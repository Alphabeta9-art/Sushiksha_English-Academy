package com.example.sushikhasacademyapp;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.SaveKt;
import androidx.compose.material.icons.filled.VerifiedUserKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
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
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdminStudentDetailsActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$AdminStudentDetailsActivityKt {
    public static final ComposableSingletons$AdminStudentDetailsActivityKt INSTANCE = new ComposableSingletons$AdminStudentDetailsActivityKt();

    /* JADX INFO: renamed from: lambda$-1462756219, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f148lambda$1462756219 = ComposableLambdaKt.composableLambdaInstance(-1462756219, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminStudentDetailsActivityKt.lambda__1462756219$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$566898661 = ComposableLambdaKt.composableLambdaInstance(566898661, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminStudentDetailsActivityKt.lambda_566898661$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1977303570, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f150lambda$1977303570 = ComposableLambdaKt.composableLambdaInstance(-1977303570, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminStudentDetailsActivityKt.lambda__1977303570$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2079452692, reason: not valid java name */
    private static Function3<ColumnScope, Composer, Integer, Unit> f151lambda$2079452692 = ComposableLambdaKt.composableLambdaInstance(-2079452692, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminStudentDetailsActivityKt.lambda__2079452692$lambda$5((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1744220692, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f149lambda$1744220692 = ComposableLambdaKt.composableLambdaInstance(-1744220692, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminStudentDetailsActivityKt.lambda__1744220692$lambda$6((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1114801618, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f147lambda$1114801618 = ComposableLambdaKt.composableLambdaInstance(-1114801618, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminStudentDetailsActivityKt.lambda__1114801618$lambda$7((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1114801618$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8615getLambda$1114801618$app() {
        return f147lambda$1114801618;
    }

    /* JADX INFO: renamed from: getLambda$-1462756219$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8616getLambda$1462756219$app() {
        return f148lambda$1462756219;
    }

    /* JADX INFO: renamed from: getLambda$-1744220692$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8617getLambda$1744220692$app() {
        return f149lambda$1744220692;
    }

    /* JADX INFO: renamed from: getLambda$-1977303570$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8618getLambda$1977303570$app() {
        return f150lambda$1977303570;
    }

    /* JADX INFO: renamed from: getLambda$-2079452692$app, reason: not valid java name */
    public final Function3<ColumnScope, Composer, Integer, Unit> m8619getLambda$2079452692$app() {
        return f151lambda$2079452692;
    }

    public final Function2<Composer, Integer, Unit> getLambda$566898661$app() {
        return lambda$566898661;
    }

    static final Unit lambda__1462756219$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C103@4762L74:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1462756219, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt.lambda$-1462756219.<anonymous> (AdminStudentDetailsActivity.kt:103)");
            }
            TextKt.m3072TextNvy7gAk("Admin • Student Details & Attendance", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_566898661$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C104@4906L70:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(566898661, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt.lambda$566898661.<anonymous> (AdminStudentDetailsActivity.kt:104)");
            }
            IconKt.m2520Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1977303570$lambda$2(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C124@5902L22:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1977303570, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt.lambda$-1977303570.<anonymous> (AdminStudentDetailsActivity.kt:124)");
            }
            TextKt.m3072TextNvy7gAk("Select Student", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2079452692$lambda$5(ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C148@7406L1059:AdminStudentDetailsActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2079452692, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt.lambda$-2079452692.<anonymous> (AdminStudentDetailsActivity.kt:148)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1756728078, "C152@7574L193,157@7788L29,158@7838L609:AdminStudentDetailsActivity.kt#t7vdsr");
            IconKt.m2520Iconww6aTOc(VerifiedUserKt.getVerifiedUser(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4279599165L), $composer, 3120, 4);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function1 = constructor2;
                $composer.createNode(function1);
            } else {
                function1 = constructor2;
                $composer.useNode();
            }
            Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 341496647, "C161@8001L10,159@7871L267,166@8324L10,164@8163L262:AdminStudentDetailsActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("Status: Account Active & Authorized", null, ColorKt.Color(4279599165L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
            TextKt.m3072TextNvy7gAk("Student can log in and access courses without admin authorization.", null, ColorKt.Color(4282865001L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 390, 0, 131066);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
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
        return Unit.INSTANCE;
    }

    static final Unit lambda__1744220692$lambda$6(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C180@8745L85,181@8847L28,182@8892L96:AdminStudentDetailsActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1744220692, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt.lambda$-1744220692.<anonymous> (AdminStudentDetailsActivity.kt:180)");
            }
            IconKt.m2520Iconww6aTOc(VerifiedUserKt.getVerifiedUser(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4279673674L), $composer, 3120, 4);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Account Active & Authorized", null, ColorKt.Color(4279673674L), null, 0L, null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1573254, 0, 262074);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1114801618$lambda$7(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C214@10882L51,215@10950L28,216@10995L28:AdminStudentDetailsActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1114801618, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$AdminStudentDetailsActivityKt.lambda$-1114801618.<anonymous> (AdminStudentDetailsActivity.kt:214)");
            }
            IconKt.m2520Iconww6aTOc(SaveKt.getSave(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Save Student Details", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
