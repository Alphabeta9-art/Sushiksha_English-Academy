package com.example.sushikhasacademyapp.ui;

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
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.CloudUploadKt;
import androidx.compose.material.icons.filled.DeleteKt;
import androidx.compose.material.icons.filled.EditKt;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material.icons.filled.SaveKt;
import androidx.compose.material.icons.filled.StarKt;
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
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdminDashboardDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$AdminDashboardDialogKt {
    public static final ComposableSingletons$AdminDashboardDialogKt INSTANCE = new ComposableSingletons$AdminDashboardDialogKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$2001932004 = ComposableLambdaKt.composableLambdaInstance(2001932004, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_2001932004$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1917966581 = ComposableLambdaKt.composableLambdaInstance(1917966581, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1917966581$lambda$1((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1237405104, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f242lambda$1237405104 = ComposableLambdaKt.composableLambdaInstance(-1237405104, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda22
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1237405104$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1248331295 = ComposableLambdaKt.composableLambdaInstance(1248331295, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda33
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1248331295$lambda$4((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1581515720 = ComposableLambdaKt.composableLambdaInstance(1581515720, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda44
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1581515720$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1447408647 = ComposableLambdaKt.composableLambdaInstance(1447408647, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda53
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1447408647$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1049562726, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f238lambda$1049562726 = ComposableLambdaKt.composableLambdaInstance(-1049562726, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda54
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1049562726$lambda$8((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$1012176670 = ComposableLambdaKt.composableLambdaInstance(1012176670, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda55
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1012176670$lambda$9((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$291401749 = ComposableLambdaKt.composableLambdaInstance(291401749, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda56
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_291401749$lambda$10((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$1109297110 = ComposableLambdaKt.composableLambdaInstance(1109297110, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda57
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1109297110$lambda$11((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$1927192471 = ComposableLambdaKt.composableLambdaInstance(1927192471, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1927192471$lambda$12((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1549879464, reason: not valid java name */
    private static Function3<ColumnScope, Composer, Integer, Unit> f246lambda$1549879464 = ComposableLambdaKt.composableLambdaInstance(-1549879464, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1549879464$lambda$13((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1930741769 = ComposableLambdaKt.composableLambdaInstance(1930741769, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1930741769$lambda$14((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-891946934, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f257lambda$891946934 = ComposableLambdaKt.composableLambdaInstance(-891946934, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__891946934$lambda$15((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1175010098 = ComposableLambdaKt.composableLambdaInstance(1175010098, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1175010098$lambda$16((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1304469709, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f244lambda$1304469709 = ComposableLambdaKt.composableLambdaInstance(-1304469709, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1304469709$lambda$17((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-865665007, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f255lambda$865665007 = ComposableLambdaKt.composableLambdaInstance(-865665007, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__865665007$lambda$18((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$949822482 = ComposableLambdaKt.composableLambdaInstance(949822482, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_949822482$lambda$19((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1388627184 = ComposableLambdaKt.composableLambdaInstance(1388627184, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1388627184$lambda$20((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1090852623, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f239lambda$1090852623 = ComposableLambdaKt.composableLambdaInstance(-1090852623, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1090852623$lambda$21((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$1209580977 = ComposableLambdaKt.composableLambdaInstance(1209580977, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1209580977$lambda$23((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-652047921, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f254lambda$652047921 = ComposableLambdaKt.composableLambdaInstance(-652047921, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__652047921$lambda$24((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1163439568 = ComposableLambdaKt.composableLambdaInstance(1163439568, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1163439568$lambda$25((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1602244270 = ComposableLambdaKt.composableLambdaInstance(1602244270, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1602244270$lambda$26((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-877235537, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f256lambda$877235537 = ComposableLambdaKt.composableLambdaInstance(-877235537, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__877235537$lambda$27((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1337568755 = ComposableLambdaKt.composableLambdaInstance(1337568755, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1337568755$lambda$28((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1930669225 = ComposableLambdaKt.composableLambdaInstance(1930669225, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda18
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1930669225$lambda$29((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$990088959 = ComposableLambdaKt.composableLambdaInstance(990088959, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda19
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_990088959$lambda$30((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$274541032 = ComposableLambdaKt.composableLambdaInstance(274541032, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda20
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_274541032$lambda$31((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1603941850, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f248lambda$1603941850 = ComposableLambdaKt.composableLambdaInstance(-1603941850, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda21
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1603941850$lambda$32((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1138250295 = ComposableLambdaKt.composableLambdaInstance(1138250295, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda23
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1138250295$lambda$33((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1205005153 = ComposableLambdaKt.composableLambdaInstance(1205005153, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda24
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1205005153$lambda$34((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1714976522 = ComposableLambdaKt.composableLambdaInstance(1714976522, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda25
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1714976522$lambda$35((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$379602793 = ComposableLambdaKt.composableLambdaInstance(379602793, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda26
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_379602793$lambda$36((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-955770936, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f259lambda$955770936 = ComposableLambdaKt.composableLambdaInstance(-955770936, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda27
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__955770936$lambda$37((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2003822631 = ComposableLambdaKt.composableLambdaInstance(2003822631, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda28
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_2003822631$lambda$38((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$668448902 = ComposableLambdaKt.composableLambdaInstance(668448902, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda29
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_668448902$lambda$39((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$204684889 = ComposableLambdaKt.composableLambdaInstance(204684889, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda30
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_204684889$lambda$40((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-207235567, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f251lambda$207235567 = ComposableLambdaKt.composableLambdaInstance(-207235567, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda31
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__207235567$lambda$41((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1172838776, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f241lambda$1172838776 = ComposableLambdaKt.composableLambdaInstance(-1172838776, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda32
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1172838776$lambda$42((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-908817911, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f258lambda$908817911 = ComposableLambdaKt.composableLambdaInstance(-908817911, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda34
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__908817911$lambda$43((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$965041110 = ComposableLambdaKt.composableLambdaInstance(965041110, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda35
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_965041110$lambda$44((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-587734041, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f253lambda$587734041 = ComposableLambdaKt.composableLambdaInstance(-587734041, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda36
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__587734041$lambda$45((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-520979183, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f252lambda$520979183 = ComposableLambdaKt.composableLambdaInstance(-520979183, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda37
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__520979183$lambda$46((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-11007814, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f240lambda$11007814 = ComposableLambdaKt.composableLambdaInstance(-11007814, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda38
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__11007814$lambda$47((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1346381543, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f245lambda$1346381543 = ComposableLambdaKt.composableLambdaInstance(-1346381543, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda39
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1346381543$lambda$48((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1613212024 = ComposableLambdaKt.composableLambdaInstance(1613212024, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda40
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1613212024$lambda$49((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$277838295 = ComposableLambdaKt.composableLambdaInstance(277838295, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda41
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_277838295$lambda$50((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1744855281, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f249lambda$1744855281 = ComposableLambdaKt.composableLambdaInstance(-1744855281, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda42
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1744855281$lambda$51((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1955601160, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f250lambda$1955601160 = ComposableLambdaKt.composableLambdaInstance(-1955601160, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda43
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1955601160$lambda$52((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$155090199 = ComposableLambdaKt.composableLambdaInstance(155090199, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda45
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_155090199$lambda$53((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1947843705 = ComposableLambdaKt.composableLambdaInstance(1947843705, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda46
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_1947843705$lambda$54((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$791030286 = ComposableLambdaKt.composableLambdaInstance(791030286, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda47
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_791030286$lambda$55((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1565848596, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f247lambda$1565848596 = ComposableLambdaKt.composableLambdaInstance(-1565848596, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda48
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1565848596$lambda$56((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$319300841 = ComposableLambdaKt.composableLambdaInstance(319300841, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda49
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_319300841$lambda$57((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$4253792 = ComposableLambdaKt.composableLambdaInstance(4253792, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda50
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_4253792$lambda$58((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$862479584 = ComposableLambdaKt.composableLambdaInstance(862479584, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda51
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda_862479584$lambda$59((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1250023785, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f243lambda$1250023785 = ComposableLambdaKt.composableLambdaInstance(-1250023785, false, new Function3() { // from class: com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt$$ExternalSyntheticLambda52
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AdminDashboardDialogKt.lambda__1250023785$lambda$60((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1049562726$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8706getLambda$1049562726$app() {
        return f238lambda$1049562726;
    }

    /* JADX INFO: renamed from: getLambda$-1090852623$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8707getLambda$1090852623$app() {
        return f239lambda$1090852623;
    }

    /* JADX INFO: renamed from: getLambda$-11007814$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8708getLambda$11007814$app() {
        return f240lambda$11007814;
    }

    /* JADX INFO: renamed from: getLambda$-1172838776$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8709getLambda$1172838776$app() {
        return f241lambda$1172838776;
    }

    /* JADX INFO: renamed from: getLambda$-1237405104$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8710getLambda$1237405104$app() {
        return f242lambda$1237405104;
    }

    /* JADX INFO: renamed from: getLambda$-1250023785$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8711getLambda$1250023785$app() {
        return f243lambda$1250023785;
    }

    /* JADX INFO: renamed from: getLambda$-1304469709$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8712getLambda$1304469709$app() {
        return f244lambda$1304469709;
    }

    /* JADX INFO: renamed from: getLambda$-1346381543$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8713getLambda$1346381543$app() {
        return f245lambda$1346381543;
    }

    /* JADX INFO: renamed from: getLambda$-1549879464$app, reason: not valid java name */
    public final Function3<ColumnScope, Composer, Integer, Unit> m8714getLambda$1549879464$app() {
        return f246lambda$1549879464;
    }

    /* JADX INFO: renamed from: getLambda$-1565848596$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8715getLambda$1565848596$app() {
        return f247lambda$1565848596;
    }

    /* JADX INFO: renamed from: getLambda$-1603941850$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8716getLambda$1603941850$app() {
        return f248lambda$1603941850;
    }

    /* JADX INFO: renamed from: getLambda$-1744855281$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8717getLambda$1744855281$app() {
        return f249lambda$1744855281;
    }

    /* JADX INFO: renamed from: getLambda$-1955601160$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8718getLambda$1955601160$app() {
        return f250lambda$1955601160;
    }

    /* JADX INFO: renamed from: getLambda$-207235567$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8719getLambda$207235567$app() {
        return f251lambda$207235567;
    }

    /* JADX INFO: renamed from: getLambda$-520979183$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8720getLambda$520979183$app() {
        return f252lambda$520979183;
    }

    /* JADX INFO: renamed from: getLambda$-587734041$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8721getLambda$587734041$app() {
        return f253lambda$587734041;
    }

    /* JADX INFO: renamed from: getLambda$-652047921$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8722getLambda$652047921$app() {
        return f254lambda$652047921;
    }

    /* JADX INFO: renamed from: getLambda$-865665007$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8723getLambda$865665007$app() {
        return f255lambda$865665007;
    }

    /* JADX INFO: renamed from: getLambda$-877235537$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8724getLambda$877235537$app() {
        return f256lambda$877235537;
    }

    /* JADX INFO: renamed from: getLambda$-891946934$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8725getLambda$891946934$app() {
        return f257lambda$891946934;
    }

    /* JADX INFO: renamed from: getLambda$-908817911$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8726getLambda$908817911$app() {
        return f258lambda$908817911;
    }

    /* JADX INFO: renamed from: getLambda$-955770936$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8727getLambda$955770936$app() {
        return f259lambda$955770936;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$1012176670$app() {
        return lambda$1012176670;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$1109297110$app() {
        return lambda$1109297110;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1138250295$app() {
        return lambda$1138250295;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1163439568$app() {
        return lambda$1163439568;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1175010098$app() {
        return lambda$1175010098;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1205005153$app() {
        return lambda$1205005153;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$1209580977$app() {
        return lambda$1209580977;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1248331295$app() {
        return lambda$1248331295;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1337568755$app() {
        return lambda$1337568755;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1388627184$app() {
        return lambda$1388627184;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1447408647$app() {
        return lambda$1447408647;
    }

    public final Function2<Composer, Integer, Unit> getLambda$155090199$app() {
        return lambda$155090199;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1581515720$app() {
        return lambda$1581515720;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1602244270$app() {
        return lambda$1602244270;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1613212024$app() {
        return lambda$1613212024;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1714976522$app() {
        return lambda$1714976522;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1917966581$app() {
        return lambda$1917966581;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$1927192471$app() {
        return lambda$1927192471;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1930669225$app() {
        return lambda$1930669225;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1930741769$app() {
        return lambda$1930741769;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1947843705$app() {
        return lambda$1947843705;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$2001932004$app() {
        return lambda$2001932004;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2003822631$app() {
        return lambda$2003822631;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$204684889$app() {
        return lambda$204684889;
    }

    public final Function2<Composer, Integer, Unit> getLambda$274541032$app() {
        return lambda$274541032;
    }

    public final Function2<Composer, Integer, Unit> getLambda$277838295$app() {
        return lambda$277838295;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$291401749$app() {
        return lambda$291401749;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$319300841$app() {
        return lambda$319300841;
    }

    public final Function2<Composer, Integer, Unit> getLambda$379602793$app() {
        return lambda$379602793;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$4253792$app() {
        return lambda$4253792;
    }

    public final Function2<Composer, Integer, Unit> getLambda$668448902$app() {
        return lambda$668448902;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$791030286$app() {
        return lambda$791030286;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$862479584$app() {
        return lambda$862479584;
    }

    public final Function2<Composer, Integer, Unit> getLambda$949822482$app() {
        return lambda$949822482;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$965041110$app() {
        return lambda$965041110;
    }

    public final Function2<Composer, Integer, Unit> getLambda$990088959$app() {
        return lambda$990088959;
    }

    static final Unit lambda__1237405104$lambda$3(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C52@1791L365:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1237405104, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1237405104.<anonymous> (AdminDashboardDialog.kt:52)");
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
            ComposerKt.sourceInformationMarkerStart($composer, -548857890, "C53@1934L11,53@1861L93,54@1971L39,55@2084L10,55@2027L115:AdminDashboardDialog.kt#untpz1");
            IconKt.m2520Iconww6aTOc(LockKt.getLock(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Teacher Admin Portal Login", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
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

    static final Unit lambda_1248331295$lambda$4(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C69@2720L19:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1248331295, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1248331295.<anonymous> (AdminDashboardDialog.kt:69)");
            }
            TextKt.m3072TextNvy7gAk("Admin Email", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1581515720$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C77@3014L16:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1581515720, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1581515720.<anonymous> (AdminDashboardDialog.kt:77)");
            }
            TextKt.m3072TextNvy7gAk("Password", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2001932004$lambda$0(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C162@7663L22:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2001932004, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$2001932004.<anonymous> (AdminDashboardDialog.kt:162)");
            }
            TextKt.m3072TextNvy7gAk("Login as Admin", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1917966581$lambda$1(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C167@7799L14:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1917966581, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1917966581.<anonymous> (AdminDashboardDialog.kt:167)");
            }
            TextKt.m3072TextNvy7gAk("Cancel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1447408647$lambda$7(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C206@8939L326:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1447408647, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1447408647.<anonymous> (AdminDashboardDialog.kt:206)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1523143381, "C207@9018L10,207@8976L100,208@9175L10,208@9219L11,208@9105L134:AdminDashboardDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Admin Panel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
            TextKt.m3072TextNvy7gAk("Real-time sync via Firebase SQL Connect", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
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

    static final Unit lambda__1049562726$lambda$8(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C213@9407L55:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1049562726, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1049562726.<anonymous> (AdminDashboardDialog.kt:213)");
            }
            IconKt.m2520Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Close", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1012176670$lambda$9(ColumnScope Tab, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Tab, "$this$Tab");
        ComposerKt.sourceInformation($composer, "C221@9889L10,221@9812L100:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1012176670, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1012176670.<anonymous> (AdminDashboardDialog.kt:221)");
            }
            TextKt.m3072TextNvy7gAk("Teacher DP", PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_291401749$lambda$10(ColumnScope Tab, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Tab, "$this$Tab");
        ComposerKt.sourceInformation($composer, "C224@10119L10,224@10045L97:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(291401749, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$291401749.<anonymous> (AdminDashboardDialog.kt:224)");
            }
            TextKt.m3072TextNvy7gAk("Courses", PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1109297110$lambda$11(ColumnScope Tab, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Tab, "$this$Tab");
        ComposerKt.sourceInformation($composer, "C227@10349L10,227@10275L97:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1109297110, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1109297110.<anonymous> (AdminDashboardDialog.kt:227)");
            }
            TextKt.m3072TextNvy7gAk("Batches", PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1927192471$lambda$12(ColumnScope Tab, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Tab, "$this$Tab");
        ComposerKt.sourceInformation($composer, "C230@10579L10,230@10505L97:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1927192471, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1927192471.<anonymous> (AdminDashboardDialog.kt:230)");
            }
            TextKt.m3072TextNvy7gAk("Contact", PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 54, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1549879464$lambda$13(ColumnScope Tab, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Tab, "$this$Tab");
        ComposerKt.sourceInformation($composer, "C233@10817L10,233@10893L11,233@10735L178:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1549879464, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1549879464.<anonymous> (AdminDashboardDialog.kt:233)");
            }
            TextKt.m3072TextNvy7gAk("Enroll Students", PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 1572918, 0, 131000);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1930741769$lambda$14(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C297@13785L20:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1930741769, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1930741769.<anonymous> (AdminDashboardDialog.kt:297)");
            }
            TextKt.m3072TextNvy7gAk("Teacher Name", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__891946934$lambda$15(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C298@13837L24:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-891946934, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-891946934.<anonymous> (AdminDashboardDialog.kt:298)");
            }
            TextKt.m3072TextNvy7gAk("e.g. Suman Ghosh", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1175010098$lambda$16(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C305@14043L27:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1175010098, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1175010098.<anonymous> (AdminDashboardDialog.kt:305)");
            }
            TextKt.m3072TextNvy7gAk("Title / Designation", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1304469709$lambda$17(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C306@14102L42:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1304469709, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1304469709.<anonymous> (AdminDashboardDialog.kt:306)");
            }
            TextKt.m3072TextNvy7gAk("e.g. Lead English Trainer & Mentor", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__865665007$lambda$18(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C313@14338L32:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-865665007, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-865665007.<anonymous> (AdminDashboardDialog.kt:313)");
            }
            TextKt.m3072TextNvy7gAk("Degrees & Qualifications", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_949822482$lambda$19(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C314@14402L57:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(949822482, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$949822482.<anonymous> (AdminDashboardDialog.kt:314)");
            }
            TextKt.m3072TextNvy7gAk("e.g. M.A. English | 12+ Years Teaching Experience", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1388627184$lambda$20(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C321@14641L38:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1388627184, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1388627184.<anonymous> (AdminDashboardDialog.kt:321)");
            }
            TextKt.m3072TextNvy7gAk("Display Picture (DP) Image URL", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1090852623$lambda$21(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C322@14711L39:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1090852623, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1090852623.<anonymous> (AdminDashboardDialog.kt:322)");
            }
            TextKt.m3072TextNvy7gAk("https://example.com/profile.jpg", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1209580977$lambda$23(ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C331@15048L605:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1209580977, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1209580977.<anonymous> (AdminDashboardDialog.kt:331)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12));
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
            ComposerKt.sourceInformationMarkerStart($composer, 726385407, "C335@15200L77,336@15294L39,339@15494L10,340@15593L11,337@15350L289:AdminDashboardDialog.kt#untpz1");
            IconKt.m2520Iconww6aTOc(StarKt.getStar(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4293571336L), $composer, 3120, 4);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Academy Rating: 4.9 (Sushiksha English Academy, Konnagar - Fixed)", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131066);
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

    static final Unit lambda__652047921$lambda$24(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C348@15801L23:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-652047921, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-652047921.<anonymous> (AdminDashboardDialog.kt:348)");
            }
            TextKt.m3072TextNvy7gAk("Students Taught", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1163439568$lambda$25(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C349@15856L17:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1163439568, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1163439568.<anonymous> (AdminDashboardDialog.kt:349)");
            }
            TextKt.m3072TextNvy7gAk("e.g. 1500", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1602244270$lambda$26(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C356@16051L30:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1602244270, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1602244270.<anonymous> (AdminDashboardDialog.kt:356)");
            }
            TextKt.m3072TextNvy7gAk("Short Bio / Philosophy", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__877235537$lambda$27(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C357@16113L76:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-877235537, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-877235537.<anonymous> (AdminDashboardDialog.kt:357)");
            }
            TextKt.m3072TextNvy7gAk("e.g. Passionate about transforming students into confident speakers.", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1337568755$lambda$28(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C384@17113L51,385@17177L39,386@17229L38:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1337568755, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1337568755.<anonymous> (AdminDashboardDialog.kt:384)");
            }
            IconKt.m2520Iconww6aTOc(SaveKt.getSave(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Update Profile in SQL Database", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1930669225$lambda$29(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C408@17833L50,409@17896L39,410@17948L22:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1930669225, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1930669225.<anonymous> (AdminDashboardDialog.kt:408)");
            }
            IconKt.m2520Iconww6aTOc(AddKt.getAdd(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Add New Course", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_990088959$lambda$30(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C443@19501L11,443@19426L95:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(990088959, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$990088959.<anonymous> (AdminDashboardDialog.kt:443)");
            }
            IconKt.m2520Iconww6aTOc(EditKt.getEdit(Icons.INSTANCE.getDefault()), "Edit", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_274541032$lambda$31(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C451@19895L75:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274541032, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$274541032.<anonymous> (AdminDashboardDialog.kt:451)");
            }
            IconKt.m2520Iconww6aTOc(DeleteKt.getDelete(Icons.INSTANCE.getDefault()), "Delete", (Modifier) null, Color.INSTANCE.m5131getRed0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1205005153$lambda$34(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C479@21132L20:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1205005153, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1205005153.<anonymous> (AdminDashboardDialog.kt:479)");
            }
            TextKt.m3072TextNvy7gAk("Course Title", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1714976522$lambda$35(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C480@21299L19:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1714976522, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1714976522.<anonymous> (AdminDashboardDialog.kt:480)");
            }
            TextKt.m3072TextNvy7gAk("Description", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_379602793$lambda$36(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C481@21459L32:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(379602793, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$379602793.<anonymous> (AdminDashboardDialog.kt:481)");
            }
            TextKt.m3072TextNvy7gAk("Duration (e.g. 3 Months)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__955770936$lambda$37(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C482@21624L38:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-955770936, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-955770936.<anonymous> (AdminDashboardDialog.kt:482)");
            }
            TextKt.m3072TextNvy7gAk("Mode (Online/Classroom/Hybrid)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2003822631$lambda$38(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C483@21797L46:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2003822631, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$2003822631.<anonymous> (AdminDashboardDialog.kt:483)");
            }
            TextKt.m3072TextNvy7gAk("Level (Beginner/Intermediate/Advanced)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_668448902$lambda$39(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C484@21974L35:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(668448902, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$668448902.<anonymous> (AdminDashboardDialog.kt:484)");
            }
            TextKt.m3072TextNvy7gAk("Fee Structure (e.g. ₹4,999)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1603941850$lambda$32(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C504@22917L19:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1603941850, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1603941850.<anonymous> (AdminDashboardDialog.kt:504)");
            }
            TextKt.m3072TextNvy7gAk("Save Course", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1138250295$lambda$33(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C509@23092L14:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1138250295, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1138250295.<anonymous> (AdminDashboardDialog.kt:509)");
            }
            TextKt.m3072TextNvy7gAk("Cancel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_204684889$lambda$40(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C533@23709L50,534@23772L39,535@23824L21:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(204684889, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$204684889.<anonymous> (AdminDashboardDialog.kt:533)");
            }
            IconKt.m2520Iconww6aTOc(AddKt.getAdd(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Add New Batch", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__207235567$lambda$41(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C572@25634L108:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-207235567, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-207235567.<anonymous> (AdminDashboardDialog.kt:572)");
            }
            IconKt.m2520Iconww6aTOc(CloudUploadKt.getCloudUpload(Icons.INSTANCE.getDefault()), "Manage Documents & Uploads", (Modifier) null, ColorKt.Color(4279673674L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1172838776$lambda$42(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C579@26076L11,579@26001L95:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1172838776, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1172838776.<anonymous> (AdminDashboardDialog.kt:579)");
            }
            IconKt.m2520Iconww6aTOc(EditKt.getEdit(Icons.INSTANCE.getDefault()), "Edit", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__908817911$lambda$43(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C587@26467L75:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-908817911, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-908817911.<anonymous> (AdminDashboardDialog.kt:587)");
            }
            IconKt.m2520Iconww6aTOc(DeleteKt.getDelete(Icons.INSTANCE.getDefault()), "Delete", (Modifier) null, Color.INSTANCE.m5131getRed0d7_KjU(), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__520979183$lambda$46(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C615@27758L20:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-520979183, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-520979183.<anonymous> (AdminDashboardDialog.kt:615)");
            }
            TextKt.m3072TextNvy7gAk("Course Title", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__11007814$lambda$47(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C616@27915L41:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-11007814, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-11007814.<anonymous> (AdminDashboardDialog.kt:616)");
            }
            TextKt.m3072TextNvy7gAk("Timing (e.g. 08:00 AM - 09:30 AM)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1346381543$lambda$48(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C617@28089L33:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1346381543, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1346381543.<anonymous> (AdminDashboardDialog.kt:617)");
            }
            TextKt.m3072TextNvy7gAk("Days (e.g. Mon, Wed, Fri)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1613212024$lambda$49(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C618@28265L18:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1613212024, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1613212024.<anonymous> (AdminDashboardDialog.kt:618)");
            }
            TextKt.m3072TextNvy7gAk("Seats Left", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_277838295$lambda$50(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C619@28416L46:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(277838295, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$277838295.<anonymous> (AdminDashboardDialog.kt:619)");
            }
            TextKt.m3072TextNvy7gAk("Mode (e.g. Offline Classroom / Online)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_965041110$lambda$44(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C646@29920L18:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(965041110, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$965041110.<anonymous> (AdminDashboardDialog.kt:646)");
            }
            TextKt.m3072TextNvy7gAk("Save Batch", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__587734041$lambda$45(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C651@30093L14:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-587734041, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-587734041.<anonymous> (AdminDashboardDialog.kt:651)");
            }
            TextKt.m3072TextNvy7gAk("Cancel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1744855281$lambda$51(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C679@30937L23:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1744855281, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1744855281.<anonymous> (AdminDashboardDialog.kt:679)");
            }
            TextKt.m3072TextNvy7gAk("Academy Address", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1955601160$lambda$52(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C686@31142L20:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1955601160, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1955601160.<anonymous> (AdminDashboardDialog.kt:686)");
            }
            TextKt.m3072TextNvy7gAk("Phone Number", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_155090199$lambda$53(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C693@31344L21:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(155090199, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$155090199.<anonymous> (AdminDashboardDialog.kt:693)");
            }
            TextKt.m3072TextNvy7gAk("Email Address", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1947843705$lambda$54(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C712@31877L51,713@31941L39,714@31993L46:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1947843705, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$1947843705.<anonymous> (AdminDashboardDialog.kt:712)");
            }
            IconKt.m2520Iconww6aTOc(SaveKt.getSave(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Update Contact Details in SQL Database", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_791030286$lambda$55(ColumnScope Card, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C828@36766L10,830@36887L11,826@36589L344:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(791030286, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$791030286.<anonymous> (AdminDashboardDialog.kt:826)");
            }
            TextKt.m3072TextNvy7gAk("No users found in Firestore 'users' collection. Ask students to log in or create an account first.", PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnErrorContainer(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 54, 0, 131064);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1565848596$lambda$56(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C844@37497L47:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1565848596, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1565848596.<anonymous> (AdminDashboardDialog.kt:844)");
            }
            TextKt.m3072TextNvy7gAk("Select Student (from Firestore 'users')", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_319300841$lambda$57(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C921@41526L10,921@41477L71:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(319300841, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$319300841.<anonymous> (AdminDashboardDialog.kt:921)");
            }
            TextKt.m3072TextNvy7gAk("Select All Courses", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_4253792$lambda$58(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C927@41872L10,927@41828L86:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(4253792, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$4253792.<anonymous> (AdminDashboardDialog.kt:927)");
            }
            TextKt.m3072TextNvy7gAk("Clear Courses", null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_862479584$lambda$59(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C1005@46022L10,1005@45973L71:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(862479584, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$862479584.<anonymous> (AdminDashboardDialog.kt:1005)");
            }
            TextKt.m3072TextNvy7gAk("Select All Batches", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1250023785$lambda$60(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C1011@46368L10,1011@46324L86:AdminDashboardDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1250023785, $changed, -1, "com.example.sushikhasacademyapp.ui.ComposableSingletons$AdminDashboardDialogKt.lambda$-1250023785.<anonymous> (AdminDashboardDialog.kt:1011)");
            }
            TextKt.m3072TextNvy7gAk("Clear Batches", null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
