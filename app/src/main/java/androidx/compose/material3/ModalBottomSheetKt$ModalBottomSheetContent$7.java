package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ModalBottomSheet.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class ModalBottomSheetKt$ModalBottomSheetContent$7 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function0<Unit> $animateToDismiss;
    final /* synthetic */ Function3<ColumnScope, Composer, Integer, Unit> $content;
    final /* synthetic */ Function2<Composer, Integer, WindowInsets> $contentWindowInsets;
    final /* synthetic */ Function2<Composer, Integer, Unit> $dragHandle;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ boolean $sheetGesturesEnabled;
    final /* synthetic */ SheetState $sheetState;

    /* JADX WARN: Multi-variable type inference failed */
    ModalBottomSheetKt$ModalBottomSheetContent$7(Function2<? super Composer, ? super Integer, ? extends WindowInsets> function2, Animatable<Float, AnimationVector1D> animatable, SheetState sheetState, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Function0<Unit> function0, CoroutineScope coroutineScope, boolean z) {
        this.$contentWindowInsets = function2;
        this.$predictiveBackProgress = animatable;
        this.$sheetState = sheetState;
        this.$dragHandle = function3;
        this.$content = function4;
        this.$animateToDismiss = function0;
        this.$scope = coroutineScope;
        this.$sheetGesturesEnabled = z;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:34:0x0203  */
    /* JADX WARN: Code duplicated, block: B:37:0x0238  */
    /* JADX WARN: Code duplicated, block: B:40:? A[RETURN, SYNTHETIC] */
    public final void invoke(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        int compositeKeyHash$iv$iv;
        int $changed2;
        ColumnScope $this$invoke_u24lambda_u242;
        ComposerKt.sourceInformation($composer, "C361@17198L21,362@17252L586,359@17117L4277:ModalBottomSheet.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(728743275, $changed, -1, "androidx.compose.material3.ModalBottomSheetContent.<anonymous> (ModalBottomSheet.kt:359)");
        }
        Modifier modifierWindowInsetsPadding = WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), this.$contentWindowInsets.invoke($composer, 0));
        ComposerKt.sourceInformationMarkerStart($composer, 242623253, "CC(remember):ModalBottomSheet.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(this.$predictiveBackProgress);
        final Animatable<Float, AnimationVector1D> animatable = this.$predictiveBackProgress;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function1() { // from class: androidx.compose.material3.ModalBottomSheetKt$ModalBottomSheetContent$7$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ModalBottomSheetKt$ModalBottomSheetContent$7.invoke$lambda$1$lambda$0(animatable, (GraphicsLayerScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv = BottomSheetScaffoldKt.verticalScaleDown(GraphicsLayerModifierKt.graphicsLayer(modifierWindowInsetsPadding, (Function1) it$iv), this.$sheetState);
        Function2<Composer, Integer, Unit> function2 = this.$dragHandle;
        Function3<ColumnScope, Composer, Integer, Unit> function3 = this.$content;
        SheetState sheetState = this.$sheetState;
        Function0<Unit> function1 = this.$animateToDismiss;
        CoroutineScope coroutineScope = this.$scope;
        boolean z = this.$sheetGesturesEnabled;
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv = (0 << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
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
        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
            if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            $changed2 = ((0 >> 6) & 112) | 6;
            $this$invoke_u24lambda_u242 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1352912384, "C432@21375L9:ModalBottomSheet.kt#uh7d8r");
            if (function2 != null) {
                $composer.startReplaceGroup(1352934765);
                ComposerKt.sourceInformation($composer, "379@18206L54,380@18302L48,381@18391L47,382@18477L2871,382@18455L2893");
                Strings.Companion companion = Strings.INSTANCE;
                String collapseActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_collapse_description), $composer, 0);
                Strings.Companion companion2 = Strings.INSTANCE;
                String dismissActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), $composer, 0);
                Strings.Companion companion3 = Strings.INSTANCE;
                String expandActionLabel = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_expand_description), $composer, 0);
                SheetDefaultsKt.DragHandleWithTooltip($this$invoke_u24lambda_u242, ComposableLambdaKt.rememberComposableLambda(2000500644, true, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1(sheetState, function1, coroutineScope, z, dismissActionLabel, expandActionLabel, collapseActionLabel, function2), $composer, 54), $composer, ($changed2 & 14) | 48);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1356009965);
                $composer.endReplaceGroup();
            }
            function3.invoke($this$invoke_u24lambda_u242, $composer, Integer.valueOf($changed2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
        int i2 = ($changed$iv$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
        $changed2 = ((0 >> 6) & 112) | 6;
        $this$invoke_u24lambda_u242 = ColumnScopeInstance.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1352912384, "C432@21375L9:ModalBottomSheet.kt#uh7d8r");
        if (function2 != null) {
            $composer.startReplaceGroup(1352934765);
            ComposerKt.sourceInformation($composer, "379@18206L54,380@18302L48,381@18391L47,382@18477L2871,382@18455L2893");
            Strings.Companion companion4 = Strings.INSTANCE;
            String collapseActionLabel2 = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_collapse_description), $composer, 0);
            Strings.Companion companion5 = Strings.INSTANCE;
            String dismissActionLabel2 = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_dismiss_description), $composer, 0);
            Strings.Companion companion6 = Strings.INSTANCE;
            String expandActionLabel2 = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_bottom_sheet_expand_description), $composer, 0);
            SheetDefaultsKt.DragHandleWithTooltip($this$invoke_u24lambda_u242, ComposableLambdaKt.rememberComposableLambda(2000500644, true, new ModalBottomSheetKt$ModalBottomSheetContent$7$2$1(sheetState, function1, coroutineScope, z, dismissActionLabel2, expandActionLabel2, collapseActionLabel2, function2), $composer, 54), $composer, ($changed2 & 14) | 48);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(1356009965);
            $composer.endReplaceGroup();
        }
        function3.invoke($this$invoke_u24lambda_u242, $composer, Integer.valueOf($changed2 & 14));
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    static final Unit invoke$lambda$1$lambda$0(Animatable $predictiveBackProgress, GraphicsLayerScope $this$graphicsLayer) {
        float progress = ((Number) $predictiveBackProgress.getValue()).floatValue();
        float predictiveBackScaleX = ModalBottomSheetKt.calculatePredictiveBackScaleX($this$graphicsLayer, progress);
        float predictiveBackScaleY = ModalBottomSheetKt.calculatePredictiveBackScaleY($this$graphicsLayer, progress);
        $this$graphicsLayer.setScaleY(!((predictiveBackScaleY > 0.0f ? 1 : (predictiveBackScaleY == 0.0f ? 0 : -1)) == 0) ? predictiveBackScaleX / predictiveBackScaleY : 1.0f);
        $this$graphicsLayer.mo5298setTransformOrigin__ExYCQ(ModalBottomSheetKt.PredictiveBackChildTransformOrigin);
        return Unit.INSTANCE;
    }
}
