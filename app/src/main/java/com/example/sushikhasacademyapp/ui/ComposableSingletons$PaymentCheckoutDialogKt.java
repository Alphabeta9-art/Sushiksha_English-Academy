package com.example.sushikhasacademyapp.ui;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.QrCodeKt;
import androidx.compose.material.icons.filled.ReceiptKt;
import androidx.compose.material.icons.filled.SmartphoneKt;
import androidx.compose.material.icons.filled.VerifiedKt;
import androidx.compose.material3.IconKt;
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
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PaymentCheckoutDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$PaymentCheckoutDialogKt {
    public static final ComposableSingletons$PaymentCheckoutDialogKt INSTANCE = new ComposableSingletons$PaymentCheckoutDialogKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1259786150 = ComposableLambdaKt.composableLambdaInstance(1259786150, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda_1259786150$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1850332434 = ComposableLambdaKt.composableLambdaInstance(1850332434, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda_1850332434$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1930277117, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f263lambda$1930277117 = ComposableLambdaKt.composableLambdaInstance(-1930277117, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda__1930277117$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1449631651 = ComposableLambdaKt.composableLambdaInstance(1449631651, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda_1449631651$lambda$4((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$228414412 = ComposableLambdaKt.composableLambdaInstance(228414412, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda_228414412$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-205292503, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f265lambda$205292503 = ComposableLambdaKt.composableLambdaInstance(-205292503, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda__205292503$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$326022645 = ComposableLambdaKt.composableLambdaInstance(326022645, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda_326022645$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$145505042 = ComposableLambdaKt.composableLambdaInstance(145505042, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda_145505042$lambda$8((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-145963817, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f261lambda$145963817 = ComposableLambdaKt.composableLambdaInstance(-145963817, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda__145963817$lambda$9((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2028819491, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f264lambda$2028819491 = ComposableLambdaKt.composableLambdaInstance(-2028819491, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda__2028819491$lambda$10((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1735206178, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f262lambda$1735206178 = ComposableLambdaKt.composableLambdaInstance(-1735206178, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda__1735206178$lambda$11((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1298707129, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f260lambda$1298707129 = ComposableLambdaKt.composableLambdaInstance(-1298707129, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$PaymentCheckoutDialogKt.lambda__1298707129$lambda$12((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1298707129$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8728getLambda$1298707129$app() {
        return f260lambda$1298707129;
    }

    /* JADX INFO: renamed from: getLambda$-145963817$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8729getLambda$145963817$app() {
        return f261lambda$145963817;
    }

    /* JADX INFO: renamed from: getLambda$-1735206178$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8730getLambda$1735206178$app() {
        return f262lambda$1735206178;
    }

    /* JADX INFO: renamed from: getLambda$-1930277117$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8731getLambda$1930277117$app() {
        return f263lambda$1930277117;
    }

    /* JADX INFO: renamed from: getLambda$-2028819491$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8732getLambda$2028819491$app() {
        return f264lambda$2028819491;
    }

    /* JADX INFO: renamed from: getLambda$-205292503$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8733getLambda$205292503$app() {
        return f265lambda$205292503;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1259786150$app() {
        return lambda$1259786150;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1449631651$app() {
        return lambda$1449631651;
    }

    public final Function2<Composer, Integer, Unit> getLambda$145505042$app() {
        return lambda$145505042;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1850332434$app() {
        return lambda$1850332434;
    }

    public final Function2<Composer, Integer, Unit> getLambda$228414412$app() {
        return lambda$228414412;
    }

    public final Function2<Composer, Integer, Unit> getLambda$326022645$app() {
        return lambda$326022645;
    }

    static final Unit lambda_1850332434$lambda$2(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C97@4087L370:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1850332434, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$1850332434.<anonymous> (PaymentCheckoutDialog.kt:97)");
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
            ComposerKt.sourceInformationMarkerStart($composer, 1295928063, "C98@4161L114,99@4296L39,100@4356L83:PaymentCheckoutDialog.kt#untpz1");
            IconKt.m2520Iconww6aTOc(VerifiedKt.getVerified(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(28)), ColorKt.Color(4279673674L), $composer, 3504, 0);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Payment Reference Submitted", null, 0L, null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 262062);
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

    static final Unit lambda_1259786150$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C133@6482L12:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1259786150, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$1259786150.<anonymous> (PaymentCheckoutDialog.kt:133)");
            }
            TextKt.m3072TextNvy7gAk("Done", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1930277117$lambda$3(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C152@7033L62:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1930277117, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$-1930277117.<anonymous> (PaymentCheckoutDialog.kt:152)");
            }
            TextKt.m3072TextNvy7gAk("Fee Payment & Enrollment", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1449631651$lambda$4(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C155@7224L55:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1449631651, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$1449631651.<anonymous> (PaymentCheckoutDialog.kt:155)");
            }
            IconKt.m2520Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Close", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_228414412$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C200@9932L56:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(228414412, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$228414412.<anonymous> (PaymentCheckoutDialog.kt:200)");
            }
            TextKt.m3072TextNvy7gAk("Direct UPI App Pay", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__205292503$lambda$6(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C201@10036L57:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-205292503, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$-205292503.<anonymous> (PaymentCheckoutDialog.kt:201)");
            }
            IconKt.m2520Iconww6aTOc(SmartphoneKt.getSmartphone(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_326022645$lambda$7(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C207@10388L58:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(326022645, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$326022645.<anonymous> (PaymentCheckoutDialog.kt:207)");
            }
            TextKt.m3072TextNvy7gAk("UPI ID / QR / Ref ID", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_145505042$lambda$8(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C208@10494L53:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(145505042, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$145505042.<anonymous> (PaymentCheckoutDialog.kt:208)");
            }
            IconKt.m2520Iconww6aTOc(QrCodeKt.getQrCode(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__145963817$lambda$9(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C270@14185L58,271@14280L39,272@14356L34:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-145963817, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$-145963817.<anonymous> (PaymentCheckoutDialog.kt:270)");
            }
            IconKt.m2520Iconww6aTOc(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("I Have Completed Payment ✓", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2028819491$lambda$10(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C290@15447L43:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2028819491, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$-2028819491.<anonymous> (PaymentCheckoutDialog.kt:290)");
            }
            TextKt.m3072TextNvy7gAk("Enter UPI Transaction Ref / UTR No.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1735206178$lambda$11(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C291@15546L25:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1735206178, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$-1735206178.<anonymous> (PaymentCheckoutDialog.kt:291)");
            }
            TextKt.m3072TextNvy7gAk("e.g. 432198765012", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1298707129$lambda$12(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C302@16230L54,303@16321L39,304@16397L40:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1298707129, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$PaymentCheckoutDialogKt.lambda$-1298707129.<anonymous> (PaymentCheckoutDialog.kt:302)");
            }
            IconKt.m2520Iconww6aTOc(ReceiptKt.getReceipt(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Submit Payment & Get Fee Receipt", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
