package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
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
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.node.ComposeUiNode;
import com.facebook.common.util.UriUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppBarRow.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001aS\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\r¨\u0006\u000e²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u0084\u0002"}, d2 = {"AppBarRow", "", "overflowIndicator", "Lkotlin/Function1;", "Landroidx/compose/material3/AppBarMenuState;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "maxItemCount", "", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/material3/AppBarRowScope;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;ILkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "material3", "scope", "Landroidx/compose/material3/AppBarRowScopeImpl;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AppBarRowKt {
    static final Unit AppBarRow$lambda$5(Function3 function3, Modifier modifier, int i, Function1 function1, int i2, int i3, Composer composer, int i4) {
        AppBarRow(function3, modifier, i, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:108:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:94:0x0229  */
    /* JADX WARN: Code duplicated, block: B:97:0x0235  */
    /* JADX WARN: Code duplicated, block: B:98:0x023b  */
    public static final void AppBarRow(final Function3<? super AppBarMenuState, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, int maxItemCount, final Function1<? super AppBarRowScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        int maxItemCount2;
        final Modifier modifier3;
        final int maxItemCount3;
        Modifier.Companion modifier4;
        int $changed$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        Composer $composer2 = $composer.startRestartGroup(1891322548);
        ComposerKt.sourceInformation($composer2, "C(AppBarRow)N(overflowIndicator,modifier,maxItemCount,content)57@2869L29,58@2916L108,61@3045L30,62@3100L29,64@3162L132,71@3363L50,72@3431L680,68@3300L901:AppBarRow.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            maxItemCount2 = maxItemCount;
        } else if (($changed & 384) == 0) {
            maxItemCount2 = maxItemCount;
            $dirty |= $composer2.changed(maxItemCount2) ? 256 : 128;
        } else {
            maxItemCount2 = maxItemCount;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            maxItemCount3 = maxItemCount2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                maxItemCount2 = Integer.MAX_VALUE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1891322548, $dirty2, -1, "androidx.compose.material3.AppBarRow (AppBarRow.kt:56)");
            }
            final State latestContent = SnapshotStateKt.rememberUpdatedState(function1, $composer2, ($dirty2 >> 9) & 14);
            ComposerKt.sourceInformationMarkerStart($composer2, 789813920, "CC(remember):AppBarRow.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.AppBarRowKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppBarRowKt.AppBarRow$lambda$1$lambda$0(latestContent);
                    }
                });
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final State scope$delegate = (State) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 789817970, "CC(remember):AppBarRow.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new AppBarMenuState();
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            AppBarMenuState menuState = (AppBarMenuState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AppBarOverflowState overflowState = AppBarDslKt.rememberAppBarOverflowState($composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 789821816, "CC(remember):AppBarRow.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(overflowState) | (($dirty2 & 896) == 256);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new OverflowMeasurePolicy(overflowState, maxItemCount2, false);
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            OverflowMeasurePolicy measurePolicy = (OverflowMeasurePolicy) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            List contents$iv = CollectionsKt.listOf((Object[]) new Function2[]{ComposableLambdaKt.rememberComposableLambda(68955781, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt.AppBarRow.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C*71@3394L15:AppBarRow.kt#uh7d8r");
                    if ($composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(68955781, $changed2, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:71)");
                        }
                        List<AppBarItem> items = AppBarRowKt.AppBarRow$lambda$2(scope$delegate).getItems();
                        int size = items.size();
                        for (int index$iv = 0; index$iv < size; index$iv++) {
                            Object item$iv = items.get(index$iv);
                            AppBarItem it = (AppBarItem) item$iv;
                            it.AppbarContent($composer3, 0);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), ComposableLambdaKt.rememberComposableLambda(-815780026, true, new AnonymousClass2(function3, menuState, overflowState, scope$delegate), $composer2, 54)});
            int $changed$iv2 = ($dirty2 & 112) | 6;
            Modifier modifier$iv = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(contents$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, -290764973, "CC(remember):Layout.kt#9igjgp");
            boolean invalid$iv$iv = ((($changed$iv2 & 896) ^ 384) > 256 && $composer2.changed(measurePolicy)) || ($changed$iv2 & 384) == 256;
            Object it$iv$iv = $composer2.rememberedValue();
            if (!invalid$iv$iv) {
                $changed$iv = $changed$iv2;
                if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                }
                MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                int $changed$iv$iv = $changed$iv & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                function2CombineAsVirtualLayouts.invoke($composer2, Integer.valueOf(($changed$iv$iv$iv >> 6) & 14));
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                maxItemCount3 = maxItemCount2;
            } else {
                $changed$iv = $changed$iv2;
            }
            Object value$iv$iv = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy);
            $composer2.updateRememberedValue(value$iv$iv);
            it$iv$iv = value$iv$iv;
            MeasurePolicy measurePolicy$iv$iv2 = (MeasurePolicy) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv$iv2 = $changed$iv & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts.invoke($composer2, Integer.valueOf(($changed$iv$iv$iv2 >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            maxItemCount3 = maxItemCount2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarRowKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarRowKt.AppBarRow$lambda$5(function3, modifier3, maxItemCount3, function1, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppBarRowScopeImpl AppBarRow$lambda$2(State<AppBarRowScopeImpl> state) {
        Object thisObj$iv = state.getValue();
        return (AppBarRowScopeImpl) thisObj$iv;
    }

    static final AppBarRowScopeImpl AppBarRow$lambda$1$lambda$0(State $latestContent) {
        AppBarRowScopeImpl appBarRowScopeImpl = new AppBarRowScopeImpl(new AppBarScopeImpl());
        ((Function1) $latestContent.getValue()).invoke(appBarRowScopeImpl);
        return appBarRowScopeImpl;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarRowKt$AppBarRow$2, reason: invalid class name */
    /* JADX INFO: compiled from: AppBarRow.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ AppBarMenuState $menuState;
        final /* synthetic */ Function3<AppBarMenuState, Composer, Integer, Unit> $overflowIndicator;
        final /* synthetic */ AppBarOverflowState $overflowState;
        final /* synthetic */ State<AppBarRowScopeImpl> $scope$delegate;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function3<? super AppBarMenuState, ? super Composer, ? super Integer, Unit> function3, AppBarMenuState appBarMenuState, AppBarOverflowState appBarOverflowState, State<AppBarRowScopeImpl> state) {
            this.$overflowIndicator = function3;
            this.$menuState = appBarMenuState;
            this.$overflowState = appBarOverflowState;
            this.$scope$delegate = state;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C73@3453L640:AppBarRow.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-815780026, $changed, -1, "androidx.compose.material3.AppBarRow.<anonymous> (AppBarRow.kt:73)");
            }
            Function3<AppBarMenuState, Composer, Integer, Unit> function3 = this.$overflowIndicator;
            final AppBarMenuState appBarMenuState = this.$menuState;
            final AppBarOverflowState appBarOverflowState = this.$overflowState;
            final State<AppBarRowScopeImpl> state = this.$scope$delegate;
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Modifier modifier$iv = Modifier.INSTANCE;
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
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
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -337072597, "C74@3483L28,77@3658L23,78@3709L362,75@3536L535:AppBarRow.kt#uh7d8r");
            function3.invoke(appBarMenuState, $composer, 6);
            boolean zIsExpanded = appBarMenuState.isExpanded();
            ComposerKt.sourceInformationMarkerStart($composer, 1790247043, "CC(remember):AppBarRow.kt#9igjgp");
            Object value$iv = $composer.rememberedValue();
            if (value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: androidx.compose.material3.AppBarRowKt$AppBarRow$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppBarRowKt.AnonymousClass2.invoke$lambda$2$lambda$1$lambda$0(appBarMenuState);
                    }
                };
                $composer.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            AndroidMenu_androidKt.m2069DropdownMenuIlH_yew(zIsExpanded, (Function0) value$iv, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1786124721, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarRowKt$AppBarRow$2$1$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                    invoke(columnScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ColumnScope $this$DropdownMenu, Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C*84@4021L22:AppBarRow.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1786124721, $changed2, -1, "androidx.compose.material3.AppBarRow.<anonymous>.<anonymous>.<anonymous> (AppBarRow.kt:79)");
                    }
                    List<AppBarItem> listSubList = AppBarRowKt.AppBarRow$lambda$2(state).getItems().subList(appBarOverflowState.getVisibleItemCount(), appBarOverflowState.getTotalItemCount());
                    AppBarMenuState appBarMenuState2 = appBarMenuState;
                    int size = listSubList.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = listSubList.get(index$iv);
                        AppBarItem item = (AppBarItem) item$iv;
                        item.MenuContent(appBarMenuState2, $composer2, 6);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54), $composer, 48, 48, 2044);
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

        static final Unit invoke$lambda$2$lambda$1$lambda$0(AppBarMenuState $menuState) {
            $menuState.dismiss();
            return Unit.INSTANCE;
        }
    }
}
