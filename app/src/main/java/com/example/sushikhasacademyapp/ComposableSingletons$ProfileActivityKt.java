package com.example.sushikhasacademyapp;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.ArrowBackKt;
import androidx.compose.material.icons.filled.DateRangeKt;
import androidx.compose.material.icons.filled.EditKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$ProfileActivityKt {
    public static final ComposableSingletons$ProfileActivityKt INSTANCE = new ComposableSingletons$ProfileActivityKt();

    /* JADX INFO: renamed from: lambda$-805760850, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f235lambda$805760850 = ComposableLambdaKt.composableLambdaInstance(-805760850, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda__805760850$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-467473300, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f233lambda$467473300 = ComposableLambdaKt.composableLambdaInstance(-467473300, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda__467473300$lambda$1((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-141017392, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f230lambda$141017392 = ComposableLambdaKt.composableLambdaInstance(-141017392, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda__141017392$lambda$2((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-841542049, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f236lambda$841542049 = ComposableLambdaKt.composableLambdaInstance(-841542049, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda__841542049$lambda$3((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-358534428, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f231lambda$358534428 = ComposableLambdaKt.composableLambdaInstance(-358534428, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda__358534428$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1140717195, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f228lambda$1140717195 = ComposableLambdaKt.composableLambdaInstance(-1140717195, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda__1140717195$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$421663852 = ComposableLambdaKt.composableLambdaInstance(421663852, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda_421663852$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1929839851 = ComposableLambdaKt.composableLambdaInstance(1929839851, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda_1929839851$lambda$8((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1091182347 = ComposableLambdaKt.composableLambdaInstance(1091182347, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda_1091182347$lambda$9((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2088443010 = ComposableLambdaKt.composableLambdaInstance(2088443010, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda_2088443010$lambda$10((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1912232557 = ComposableLambdaKt.composableLambdaInstance(1912232557, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda_1912232557$lambda$11((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-892166034, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f237lambda$892166034 = ComposableLambdaKt.composableLambdaInstance(-892166034, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda__892166034$lambda$12((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$59665805 = ComposableLambdaKt.composableLambdaInstance(59665805, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda_59665805$lambda$13((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-402625347, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f232lambda$402625347 = ComposableLambdaKt.composableLambdaInstance(-402625347, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda__402625347$lambda$14((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-580614115, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f234lambda$580614115 = ComposableLambdaKt.composableLambdaInstance(-580614115, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$ProfileActivityKt.lambda__580614115$lambda$15((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$728772859 = ComposableLambdaKt.composableLambdaInstance(728772859, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda_728772859$lambda$16((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1389339384 = ComposableLambdaKt.composableLambdaInstance(1389339384, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda_1389339384$lambda$17((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1227129562, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f229lambda$1227129562 = ComposableLambdaKt.composableLambdaInstance(-1227129562, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$ProfileActivityKt.lambda__1227129562$lambda$18((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1140717195$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8696getLambda$1140717195$app() {
        return f228lambda$1140717195;
    }

    /* JADX INFO: renamed from: getLambda$-1227129562$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8697getLambda$1227129562$app() {
        return f229lambda$1227129562;
    }

    /* JADX INFO: renamed from: getLambda$-141017392$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8698getLambda$141017392$app() {
        return f230lambda$141017392;
    }

    /* JADX INFO: renamed from: getLambda$-358534428$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8699getLambda$358534428$app() {
        return f231lambda$358534428;
    }

    /* JADX INFO: renamed from: getLambda$-402625347$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8700getLambda$402625347$app() {
        return f232lambda$402625347;
    }

    /* JADX INFO: renamed from: getLambda$-467473300$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8701getLambda$467473300$app() {
        return f233lambda$467473300;
    }

    /* JADX INFO: renamed from: getLambda$-580614115$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8702getLambda$580614115$app() {
        return f234lambda$580614115;
    }

    /* JADX INFO: renamed from: getLambda$-805760850$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8703getLambda$805760850$app() {
        return f235lambda$805760850;
    }

    /* JADX INFO: renamed from: getLambda$-841542049$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8704getLambda$841542049$app() {
        return f236lambda$841542049;
    }

    /* JADX INFO: renamed from: getLambda$-892166034$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8705getLambda$892166034$app() {
        return f237lambda$892166034;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1091182347$app() {
        return lambda$1091182347;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1389339384$app() {
        return lambda$1389339384;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1912232557$app() {
        return lambda$1912232557;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1929839851$app() {
        return lambda$1929839851;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2088443010$app() {
        return lambda$2088443010;
    }

    public final Function2<Composer, Integer, Unit> getLambda$421663852$app() {
        return lambda$421663852;
    }

    public final Function2<Composer, Integer, Unit> getLambda$59665805$app() {
        return lambda$59665805;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$728772859$app() {
        return lambda$728772859;
    }

    static final Unit lambda__805760850$lambda$0(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C209@8682L10:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-805760850, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-805760850.<anonymous> (ProfileActivity.kt:209)");
            }
            TextKt.m3072TextNvy7gAk("OK", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__467473300$lambda$1(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C212@8807L14:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-467473300, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-467473300.<anonymous> (ProfileActivity.kt:212)");
            }
            TextKt.m3072TextNvy7gAk("Cancel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__358534428$lambda$5(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C222@9000L343:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-358534428, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-358534428.<anonymous> (ProfileActivity.kt:222)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((384 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 812797400, "C223@9070L77,224@9164L39,225@9271L10,225@9220L109:ProfileActivity.kt#t7vdsr");
            IconKt.m2520Iconww6aTOc(EditKt.getEdit(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4279673674L), $composer, 3120, 4);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Edit Account Details", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
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

    static final Unit lambda__1140717195$lambda$6(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C238@9764L16:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1140717195, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-1140717195.<anonymous> (ProfileActivity.kt:238)");
            }
            TextKt.m3072TextNvy7gAk("Username", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_421663852$lambda$7(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C247@10073L21:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(421663852, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$421663852.<anonymous> (ProfileActivity.kt:247)");
            }
            TextKt.m3072TextNvy7gAk("Date of Birth", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1929839851$lambda$8(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C250@10237L90:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1929839851, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$1929839851.<anonymous> (ProfileActivity.kt:250)");
            }
            IconKt.m2520Iconww6aTOc(DateRangeKt.getDateRange(Icons.INSTANCE.getDefault()), "Select DOB", (Modifier) null, ColorKt.Color(4279673674L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1091182347$lambda$9(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C267@10977L31:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1091182347, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$1091182347.<anonymous> (ProfileActivity.kt:267)");
            }
            TextKt.m3072TextNvy7gAk("Class / Education Level", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2088443010$lambda$10(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C298@12347L29:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2088443010, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$2088443010.<anonymous> (ProfileActivity.kt:298)");
            }
            TextKt.m3072TextNvy7gAk("Competitive Exam Prep", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1912232557$lambda$11(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C323@13427L19:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1912232557, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$1912232557.<anonymous> (ProfileActivity.kt:323)");
            }
            TextKt.m3072TextNvy7gAk("School Name", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__892166034$lambda$12(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C331@13727L31:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-892166034, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-892166034.<anonymous> (ProfileActivity.kt:331)");
            }
            TextKt.m3072TextNvy7gAk("New Password (Optional)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_59665805$lambda$13(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C332@13798L36:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(59665805, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$59665805.<anonymous> (ProfileActivity.kt:332)");
            }
            TextKt.m3072TextNvy7gAk("Enter new password to change", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__141017392$lambda$2(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C354@14890L20:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-141017392, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-141017392.<anonymous> (ProfileActivity.kt:354)");
            }
            TextKt.m3072TextNvy7gAk("Save Changes", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__841542049$lambda$3(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C359@15024L14:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-841542049, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-841542049.<anonymous> (ProfileActivity.kt:359)");
            }
            TextKt.m3072TextNvy7gAk("Cancel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__402625347$lambda$14(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C463@19123L48:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-402625347, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-402625347.<anonymous> (ProfileActivity.kt:463)");
            }
            TextKt.m3072TextNvy7gAk("My Profile", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__580614115$lambda$15(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C466@19285L70:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-580614115, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-580614115.<anonymous> (ProfileActivity.kt:466)");
            }
            IconKt.m2520Iconww6aTOc(ArrowBackKt.getArrowBack(Icons.AutoMirrored.Filled.INSTANCE), "Back", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_728772859$lambda$16(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C601@26297L84,602@26410L39,603@26478L58:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(728772859, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$728772859.<anonymous> (ProfileActivity.kt:601)");
            }
            IconKt.m2520Iconww6aTOc(EditKt.getEdit(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Edit Account Details", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1389339384$lambda$17(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C616@27053L92:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1389339384, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$1389339384.<anonymous> (ProfileActivity.kt:616)");
            }
            TextKt.m3072TextNvy7gAk("Contact Academy", null, Color.INSTANCE.m5134getWhite0d7_KjU(), null, TextUnitKt.getSp(16), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597830, 0, 262058);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1227129562$lambda$18(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C708@32064L10,708@32025L116:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1227129562, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$ProfileActivityKt.lambda$-1227129562.<anonymous> (ProfileActivity.kt:708)");
            }
            TextKt.m3072TextNvy7gAk("Sign Out", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyLarge(), Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777210, null), $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
