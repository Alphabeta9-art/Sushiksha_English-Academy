package androidx.compose.material;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
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
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J~\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00150\u0019¢\u0006\u0002\b\u001a2\u0013\u0010\u001c\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010\u001e\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0019¢\u0006\u0002\b\u001aH\u0007¢\u0006\u0002\u0010\u001fR\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u000f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0010\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0011\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0013\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006 "}, d2 = {"Landroidx/compose/material/TwoLine;", "", "<init>", "()V", "MinHeight", "Landroidx/compose/ui/unit/Dp;", "F", "MinHeightWithIcon", "IconMinPaddedWidth", "IconLeftPadding", "IconVerticalPadding", "ContentLeftPadding", "ContentRightPadding", "OverlineBaselineOffset", "OverlineToPrimaryBaselineOffset", "PrimaryBaselineOffsetNoIcon", "PrimaryBaselineOffsetWithIcon", "PrimaryToSecondaryBaselineOffsetNoIcon", "PrimaryToSecondaryBaselineOffsetWithIcon", "TrailingRightPadding", "ListItem", "", "modifier", "Landroidx/compose/ui/Modifier;", "icon", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "text", "secondaryText", "overlineText", "trailing", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class TwoLine {
    public static final TwoLine INSTANCE = new TwoLine();
    private static final float MinHeight = Dp.m7905constructorimpl(64);
    private static final float MinHeightWithIcon = Dp.m7905constructorimpl(72);
    private static final float IconMinPaddedWidth = Dp.m7905constructorimpl(40);
    private static final float IconLeftPadding = Dp.m7905constructorimpl(16);
    private static final float IconVerticalPadding = Dp.m7905constructorimpl(16);
    private static final float ContentLeftPadding = Dp.m7905constructorimpl(16);
    private static final float ContentRightPadding = Dp.m7905constructorimpl(16);
    private static final float OverlineBaselineOffset = Dp.m7905constructorimpl(24);
    private static final float OverlineToPrimaryBaselineOffset = Dp.m7905constructorimpl(20);
    private static final float PrimaryBaselineOffsetNoIcon = Dp.m7905constructorimpl(28);
    private static final float PrimaryBaselineOffsetWithIcon = Dp.m7905constructorimpl(32);
    private static final float PrimaryToSecondaryBaselineOffsetNoIcon = Dp.m7905constructorimpl(20);
    private static final float PrimaryToSecondaryBaselineOffsetWithIcon = Dp.m7905constructorimpl(20);
    private static final float TrailingRightPadding = Dp.m7905constructorimpl(16);

    static final Unit ListItem$lambda$1(TwoLine twoLine, Modifier modifier, Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, int i, int i2, Composer composer, int i3) {
        twoLine.ListItem(modifier, function2, function3, function4, function5, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private TwoLine() {
    }

    public final void ListItem(Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Function0<ComposeUiNode> function0;
        Composer $composer3;
        float f;
        float f2;
        float f3;
        Function0<ComposeUiNode> function1;
        Composer $composer$iv;
        Composer $composer4 = $composer.startRestartGroup(598947838);
        ComposerKt.sourceInformation($composer4, "C(ListItem)N(modifier,icon,text,secondaryText,overlineText,trailing)216@7900L2500:ListItem.kt#jmzs0o");
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
            $dirty |= $composer4.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer4.changedInstance(function4) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(function5) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer4.changedInstance(function6) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer4.changed(this) ? 1048576 : 524288;
        }
        int $dirty2 = $dirty;
        if (!$composer4.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(598947838, $dirty2, -1, "androidx.compose.material.TwoLine.ListItem (ListItem.kt:214)");
            }
            float minHeight = function2 == null ? MinHeight : MinHeightWithIcon;
            Modifier modifier$iv = SizeKt.m853heightInVpY3zN4$default(modifier2, minHeight, 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
            final float minHeight2 = minHeight;
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
            CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            $composer2 = $composer4;
            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                function0 = constructor;
                $composer4.createNode(function0);
            } else {
                function0 = constructor;
                $composer4.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
            Modifier modifier4 = modifier2;
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            int i4 = ((0 >> 6) & 112) | 6;
            RowScope $this$ListItem_u24lambda_u240 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer4, 1806926493, "C:ListItem.kt#jmzs0o");
            Modifier columnModifier = PaddingKt.m823paddingqDBjuR0$default(RowScope.weight$default($this$ListItem_u24lambda_u240, Modifier.INSTANCE, 1.0f, false, 2, null), ContentLeftPadding, 0.0f, ContentRightPadding, 0.0f, 10, null);
            if (function2 != null) {
                $composer4.startReplaceGroup(1807018686);
                ComposerKt.sourceInformation($composer4, "221@8123L546");
                Modifier.Companion companion = Modifier.INSTANCE;
                float arg0$iv = IconLeftPadding;
                float other$iv = IconMinPaddedWidth;
                Modifier modifier$iv2 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.m869sizeInqDBjuR0$default(companion, Dp.m7905constructorimpl(arg0$iv + other$iv), minHeight2, 0.0f, 0.0f, 12, null), IconLeftPadding, IconVerticalPadding, 0.0f, IconVerticalPadding, 4, null);
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (48 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    function1 = constructor2;
                    $composer4.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer4.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer4);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    $composer$iv = $composer4;
                } else {
                    $composer$iv = $composer4;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                    Composer $composer$iv2 = $composer$iv;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i6 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv2, -952520358, "C233@8645L6:ListItem.kt#jmzs0o");
                    function2.invoke($composer$iv2, Integer.valueOf(($dirty2 >> 3) & 14));
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv2);
                    $composer$iv.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                    ComposerKt.sourceInformationMarkerEnd($composer$iv);
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = ($changed$iv$iv$iv2 >> 6) & 14;
                Composer $composer$iv3 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i8 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -952520358, "C233@8645L6:ListItem.kt#jmzs0o");
                function2.invoke($composer$iv3, Integer.valueOf(($dirty2 >> 3) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
            } else {
                $composer4.startReplaceGroup(1798959616);
            }
            $composer4.endReplaceGroup();
            if (function5 != null) {
                $composer4.startReplaceGroup(1807622349);
                ComposerKt.sourceInformation($composer4, "241@8903L81,238@8741L243");
                ListItemKt.BaselinesOffsetColumn(CollectionsKt.listOf((Object[]) new Dp[]{Dp.m7903boximpl(OverlineBaselineOffset), Dp.m7903boximpl(OverlineToPrimaryBaselineOffset)}), columnModifier, ComposableLambdaKt.rememberComposableLambda(979717374, true, new Function2() { // from class: androidx.compose.material.TwoLine$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TwoLine.ListItem$lambda$0$1(function5, function3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer4, 54), $composer4, 390, 0);
                $composer3 = $composer4;
                $composer3.endReplaceGroup();
            } else {
                $composer3 = $composer4;
                $composer3.startReplaceGroup(1807914338);
                ComposerKt.sourceInformation($composer3, "260@9608L84,246@9022L670");
                Dp[] dpArr = new Dp[2];
                if (function2 != null) {
                    f = PrimaryBaselineOffsetWithIcon;
                } else {
                    f = PrimaryBaselineOffsetNoIcon;
                }
                dpArr[0] = Dp.m7903boximpl(f);
                if (function2 != null) {
                    f2 = PrimaryToSecondaryBaselineOffsetWithIcon;
                } else {
                    f2 = PrimaryToSecondaryBaselineOffsetNoIcon;
                }
                dpArr[1] = Dp.m7903boximpl(f2);
                ListItemKt.BaselinesOffsetColumn(CollectionsKt.listOf((Object[]) dpArr), columnModifier, ComposableLambdaKt.rememberComposableLambda(-1888627961, true, new Function2() { // from class: androidx.compose.material.TwoLine$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TwoLine.ListItem$lambda$0$2(function3, function4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer3, 384, 0);
                $composer3.endReplaceGroup();
            }
            if (function6 != null) {
                $composer3.startReplaceGroup(1808643799);
                ComposerKt.sourceInformation($composer3, "272@10000L376,266@9759L617");
                if (function2 != null) {
                    f3 = PrimaryBaselineOffsetWithIcon;
                } else {
                    f3 = PrimaryBaselineOffsetNoIcon;
                }
                ListItemKt.m1851OffsetToBaselineOrCenterKz89ssw(f3, null, ComposableLambdaKt.rememberComposableLambda(-1476831345, true, new Function2() { // from class: androidx.compose.material.TwoLine$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TwoLine.ListItem$lambda$0$3(minHeight2, function6, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer3, 384, 2);
            } else {
                $composer3.startReplaceGroup(1798959616);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TwoLine$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TwoLine.ListItem$lambda$1(this.f$0, modifier3, function2, function3, function4, function5, function6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ListItem$lambda$0$1(Function2 $overlineText, Function2 $text, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C242@8925L14,243@8960L6:ListItem.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(979717374, $changed, -1, "androidx.compose.material.TwoLine.ListItem.<anonymous>.<anonymous> (ListItem.kt:242)");
            }
            $overlineText.invoke($composer, 0);
            $text.invoke($composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ListItem$lambda$0$2(Function2 $text, Function2 $secondaryText, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C261@9630L6,262@9657L17:ListItem.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1888627961, $changed, -1, "androidx.compose.material.TwoLine.ListItem.<anonymous>.<anonymous> (ListItem.kt:261)");
            }
            $text.invoke($composer, 0);
            Intrinsics.checkNotNull($secondaryText);
            $secondaryText.invoke($composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ListItem$lambda$0$3(float $minHeight, Function2 $trailing, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C273@10022L336:ListItem.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1476831345, $changed, -1, "androidx.compose.material.TwoLine.ListItem.<anonymous>.<anonymous> (ListItem.kt:273)");
            }
            Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(SizeKt.m853heightInVpY3zN4$default(Modifier.INSTANCE, $minHeight, 0.0f, 2, null), 0.0f, 0.0f, TrailingRightPadding, 0.0f, 11, null);
            Alignment contentAlignment$iv = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (48 << 3) & 112;
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
            int i2 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1096526396, "C278@10326L10:ListItem.kt#jmzs0o");
            $trailing.invoke($composer, 0);
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
