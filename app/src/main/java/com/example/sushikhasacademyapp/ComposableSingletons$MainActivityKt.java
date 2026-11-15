package com.example.sushikhasacademyapp;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.filled.OpenInNewKt;
import androidx.compose.material.icons.automirrored.filled.SendKt;
import androidx.compose.material.icons.automirrored.filled.VolumeUpKt;
import androidx.compose.material.icons.filled.AdminPanelSettingsKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.DateRangeKt;
import androidx.compose.material.icons.filled.EmailKt;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material.icons.filled.MapKt;
import androidx.compose.material.icons.filled.PhoneKt;
import androidx.compose.material.icons.filled.RefreshKt;
import androidx.compose.material.icons.filled.SearchKt;
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
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.sushikhasacademyapp.data.ProfessionalWord;
import com.example.sushikhasacademyapp.data.TeacherProfile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$MainActivityKt {
    public static final ComposableSingletons$MainActivityKt INSTANCE = new ComposableSingletons$MainActivityKt();

    /* JADX INFO: renamed from: lambda$-224059377, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f221lambda$224059377 = ComposableLambdaKt.composableLambdaInstance(-224059377, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__224059377$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1121451946 = ComposableLambdaKt.composableLambdaInstance(1121451946, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1121451946$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-294223155, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f222lambda$294223155 = ComposableLambdaKt.composableLambdaInstance(-294223155, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda22
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__294223155$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1869079054 = ComposableLambdaKt.composableLambdaInstance(1869079054, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda33
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1869079054$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1784078045 = ComposableLambdaKt.composableLambdaInstance(1784078045, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda44
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1784078045$lambda$4((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-412150436, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f224lambda$412150436 = ComposableLambdaKt.composableLambdaInstance(-412150436, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda48
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__412150436$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1853647012, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f217lambda$1853647012 = ComposableLambdaKt.composableLambdaInstance(-1853647012, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda49
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1853647012$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function4<AnimatedContentScope, ProfessionalWord, Composer, Integer, Unit> lambda$1745189059 = ComposableLambdaKt.composableLambdaInstance(1745189059, false, new Function4() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda50
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$MainActivityKt.lambda_1745189059$lambda$8((AnimatedContentScope) obj, (ProfessionalWord) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2005326491 = ComposableLambdaKt.composableLambdaInstance(2005326491, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda51
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_2005326491$lambda$9((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1164486689, reason: not valid java name */
    private static Function4<AnimatedContentScope, ProfessionalWord, Composer, Integer, Unit> f207lambda$1164486689 = ComposableLambdaKt.composableLambdaInstance(-1164486689, false, new Function4() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda52
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$MainActivityKt.lambda__1164486689$lambda$11((AnimatedContentScope) obj, (ProfessionalWord) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1139491633 = ComposableLambdaKt.composableLambdaInstance(1139491633, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1139491633$lambda$12((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1622931944 = ComposableLambdaKt.composableLambdaInstance(1622931944, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1622931944$lambda$13((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1861318462, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f218lambda$1861318462 = ComposableLambdaKt.composableLambdaInstance(-1861318462, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda__1861318462$lambda$14((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$625657411 = ComposableLambdaKt.composableLambdaInstance(625657411, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_625657411$lambda$15((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1601726039 = ComposableLambdaKt.composableLambdaInstance(1601726039, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1601726039$lambda$17((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1908615437 = ComposableLambdaKt.composableLambdaInstance(1908615437, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1908615437$lambda$18((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1832776876 = ComposableLambdaKt.composableLambdaInstance(1832776876, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1832776876$lambda$19((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1109149940 = ComposableLambdaKt.composableLambdaInstance(1109149940, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1109149940$lambda$20((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$997702114 = ComposableLambdaKt.composableLambdaInstance(997702114, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_997702114$lambda$21((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1392927456, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f209lambda$1392927456 = ComposableLambdaKt.composableLambdaInstance(-1392927456, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1392927456$lambda$23((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-716414199, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f226lambda$716414199 = ComposableLambdaKt.composableLambdaInstance(-716414199, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda12
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__716414199$lambda$25((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1483673058 = ComposableLambdaKt.composableLambdaInstance(1483673058, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda13
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1483673058$lambda$26((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1696391455, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f214lambda$1696391455 = ComposableLambdaKt.composableLambdaInstance(-1696391455, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda14
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1696391455$lambda$28((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1248528462, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f208lambda$1248528462 = ComposableLambdaKt.composableLambdaInstance(-1248528462, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda15
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda__1248528462$lambda$29((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1488165959 = ComposableLambdaKt.composableLambdaInstance(1488165959, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda16
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_1488165959$lambda$30((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$500765577 = ComposableLambdaKt.composableLambdaInstance(500765577, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda17
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_500765577$lambda$31((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1737540461, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f215lambda$1737540461 = ComposableLambdaKt.composableLambdaInstance(-1737540461, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda18
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda__1737540461$lambda$32((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1821505884, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f216lambda$1821505884 = ComposableLambdaKt.composableLambdaInstance(-1821505884, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda19
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda__1821505884$lambda$33((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1803826126 = ComposableLambdaKt.composableLambdaInstance(1803826126, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda20
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1803826126$lambda$34((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2137010551 = ComposableLambdaKt.composableLambdaInstance(2137010551, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda21
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_2137010551$lambda$35((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2002427862 = ComposableLambdaKt.composableLambdaInstance(2002427862, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda23
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_2002427862$lambda$36((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$274545934 = ComposableLambdaKt.composableLambdaInstance(274545934, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda24
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_274545934$lambda$37((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$762593040 = ComposableLambdaKt.composableLambdaInstance(762593040, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda25
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda_762593040$lambda$38((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function4<AnimatedContentScope, Boolean, Composer, Integer, Unit> lambda$2144619733 = ComposableLambdaKt.composableLambdaInstance(2144619733, false, new Function4() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda26
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$MainActivityKt.lambda_2144619733$lambda$40((AnimatedContentScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, ((Integer) obj4).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1798851514 = ComposableLambdaKt.composableLambdaInstance(1798851514, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda27
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1798851514$lambda$41((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1107947545 = ComposableLambdaKt.composableLambdaInstance(1107947545, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda28
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1107947545$lambda$42((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$81595884 = ComposableLambdaKt.composableLambdaInstance(81595884, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda29
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_81595884$lambda$43((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1427350091 = ComposableLambdaKt.composableLambdaInstance(1427350091, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda30
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1427350091$lambda$44((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1521862998, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f213lambda$1521862998 = ComposableLambdaKt.composableLambdaInstance(-1521862998, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda31
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1521862998$lambda$45((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1828687971 = ComposableLambdaKt.composableLambdaInstance(1828687971, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda32
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1828687971$lambda$46((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1981642494, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f219lambda$1981642494 = ComposableLambdaKt.composableLambdaInstance(-1981642494, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda34
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1981642494$lambda$47((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1497005663, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f212lambda$1497005663 = ComposableLambdaKt.composableLambdaInstance(-1497005663, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda35
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1497005663$lambda$48((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1451060559, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f210lambda$1451060559 = ComposableLambdaKt.composableLambdaInstance(-1451060559, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda36
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__1451060559$lambda$49((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$310156112 = ComposableLambdaKt.composableLambdaInstance(310156112, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda37
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_310156112$lambda$50((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1170402992 = ComposableLambdaKt.composableLambdaInstance(1170402992, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda38
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_1170402992$lambda$51((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2045387321, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f220lambda$2045387321 = ComposableLambdaKt.composableLambdaInstance(-2045387321, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda39
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__2045387321$lambda$52((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$2102651110 = ComposableLambdaKt.composableLambdaInstance(2102651110, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda40
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_2102651110$lambda$53((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-377962242, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f223lambda$377962242 = ComposableLambdaKt.composableLambdaInstance(-377962242, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda41
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__377962242$lambda$54((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-460594328, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f225lambda$460594328 = ComposableLambdaKt.composableLambdaInstance(-460594328, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda42
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__460594328$lambda$55((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-148488121, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f211lambda$148488121 = ComposableLambdaKt.composableLambdaInstance(-148488121, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda43
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda__148488121$lambda$56((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-979317426, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f227lambda$979317426 = ComposableLambdaKt.composableLambdaInstance(-979317426, false, new Function3() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda45
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$MainActivityKt.lambda__979317426$lambda$57((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$510241912 = ComposableLambdaKt.composableLambdaInstance(510241912, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda46
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_510241912$lambda$58((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$142826414 = ComposableLambdaKt.composableLambdaInstance(142826414, false, new Function2() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda47
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MainActivityKt.lambda_142826414$lambda$61((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1164486689$app, reason: not valid java name */
    public final Function4<AnimatedContentScope, ProfessionalWord, Composer, Integer, Unit> m8675getLambda$1164486689$app() {
        return f207lambda$1164486689;
    }

    /* JADX INFO: renamed from: getLambda$-1248528462$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8676getLambda$1248528462$app() {
        return f208lambda$1248528462;
    }

    /* JADX INFO: renamed from: getLambda$-1392927456$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8677getLambda$1392927456$app() {
        return f209lambda$1392927456;
    }

    /* JADX INFO: renamed from: getLambda$-1451060559$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8678getLambda$1451060559$app() {
        return f210lambda$1451060559;
    }

    /* JADX INFO: renamed from: getLambda$-148488121$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8679getLambda$148488121$app() {
        return f211lambda$148488121;
    }

    /* JADX INFO: renamed from: getLambda$-1497005663$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8680getLambda$1497005663$app() {
        return f212lambda$1497005663;
    }

    /* JADX INFO: renamed from: getLambda$-1521862998$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8681getLambda$1521862998$app() {
        return f213lambda$1521862998;
    }

    /* JADX INFO: renamed from: getLambda$-1696391455$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8682getLambda$1696391455$app() {
        return f214lambda$1696391455;
    }

    /* JADX INFO: renamed from: getLambda$-1737540461$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8683getLambda$1737540461$app() {
        return f215lambda$1737540461;
    }

    /* JADX INFO: renamed from: getLambda$-1821505884$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8684getLambda$1821505884$app() {
        return f216lambda$1821505884;
    }

    /* JADX INFO: renamed from: getLambda$-1853647012$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8685getLambda$1853647012$app() {
        return f217lambda$1853647012;
    }

    /* JADX INFO: renamed from: getLambda$-1861318462$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8686getLambda$1861318462$app() {
        return f218lambda$1861318462;
    }

    /* JADX INFO: renamed from: getLambda$-1981642494$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8687getLambda$1981642494$app() {
        return f219lambda$1981642494;
    }

    /* JADX INFO: renamed from: getLambda$-2045387321$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8688getLambda$2045387321$app() {
        return f220lambda$2045387321;
    }

    /* JADX INFO: renamed from: getLambda$-224059377$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8689getLambda$224059377$app() {
        return f221lambda$224059377;
    }

    /* JADX INFO: renamed from: getLambda$-294223155$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8690getLambda$294223155$app() {
        return f222lambda$294223155;
    }

    /* JADX INFO: renamed from: getLambda$-377962242$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8691getLambda$377962242$app() {
        return f223lambda$377962242;
    }

    /* JADX INFO: renamed from: getLambda$-412150436$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8692getLambda$412150436$app() {
        return f224lambda$412150436;
    }

    /* JADX INFO: renamed from: getLambda$-460594328$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8693getLambda$460594328$app() {
        return f225lambda$460594328;
    }

    /* JADX INFO: renamed from: getLambda$-716414199$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8694getLambda$716414199$app() {
        return f226lambda$716414199;
    }

    /* JADX INFO: renamed from: getLambda$-979317426$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8695getLambda$979317426$app() {
        return f227lambda$979317426;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1107947545$app() {
        return lambda$1107947545;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1109149940$app() {
        return lambda$1109149940;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1121451946$app() {
        return lambda$1121451946;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1139491633$app() {
        return lambda$1139491633;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1170402992$app() {
        return lambda$1170402992;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1427350091$app() {
        return lambda$1427350091;
    }

    public final Function2<Composer, Integer, Unit> getLambda$142826414$app() {
        return lambda$142826414;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1483673058$app() {
        return lambda$1483673058;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1488165959$app() {
        return lambda$1488165959;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1601726039$app() {
        return lambda$1601726039;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1622931944$app() {
        return lambda$1622931944;
    }

    public final Function4<AnimatedContentScope, ProfessionalWord, Composer, Integer, Unit> getLambda$1745189059$app() {
        return lambda$1745189059;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1784078045$app() {
        return lambda$1784078045;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1798851514$app() {
        return lambda$1798851514;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1803826126$app() {
        return lambda$1803826126;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1828687971$app() {
        return lambda$1828687971;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1832776876$app() {
        return lambda$1832776876;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1869079054$app() {
        return lambda$1869079054;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1908615437$app() {
        return lambda$1908615437;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2002427862$app() {
        return lambda$2002427862;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2005326491$app() {
        return lambda$2005326491;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2102651110$app() {
        return lambda$2102651110;
    }

    public final Function2<Composer, Integer, Unit> getLambda$2137010551$app() {
        return lambda$2137010551;
    }

    public final Function4<AnimatedContentScope, Boolean, Composer, Integer, Unit> getLambda$2144619733$app() {
        return lambda$2144619733;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$274545934$app() {
        return lambda$274545934;
    }

    public final Function2<Composer, Integer, Unit> getLambda$310156112$app() {
        return lambda$310156112;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$500765577$app() {
        return lambda$500765577;
    }

    public final Function2<Composer, Integer, Unit> getLambda$510241912$app() {
        return lambda$510241912;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$625657411$app() {
        return lambda$625657411;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$762593040$app() {
        return lambda$762593040;
    }

    public final Function2<Composer, Integer, Unit> getLambda$81595884$app() {
        return lambda$81595884;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$997702114$app() {
        return lambda$997702114;
    }

    static final Unit lambda__224059377$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C251@11742L11,248@11559L228:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-224059377, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-224059377.<anonymous> (MainActivity.kt:248)");
            }
            IconKt.m2520Iconww6aTOc(PhoneKt.getPhone(Icons.INSTANCE.getDefault()), "Call Teacher", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1121451946$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C261@12105L241:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1121451946, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1121451946.<anonymous> (MainActivity.kt:261)");
            }
            IconKt.m2520Iconww6aTOc(AdminPanelSettingsKt.getAdminPanelSettings(Icons.INSTANCE.getDefault()), "Admin Portal", (Modifier) null, ColorKt.Color(4279673674L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1869079054$lambda$3(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C324@14983L63:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1869079054, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1869079054.<anonymous> (MainActivity.kt:324)");
            }
            IconKt.m2520Iconww6aTOc(SendKt.getSend(Icons.AutoMirrored.Filled.INSTANCE), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__294223155$lambda$2(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C325@15079L24:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-294223155, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-294223155.<anonymous> (MainActivity.kt:325)");
            }
            TextKt.m3072TextNvy7gAk("Enroll / Inquire", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1784078045$lambda$4(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C382@17997L69:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1784078045, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1784078045.<anonymous> (MainActivity.kt:382)");
            }
            TextKt.m3072TextNvy7gAk("View", null, ColorKt.Color(4283096704L), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1573254, 0, 262074);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__412150436$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C385@18222L110:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-412150436, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-412150436.<anonymous> (MainActivity.kt:385)");
            }
            IconKt.m2520Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), "Dismiss", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(18)), Color.INSTANCE.m5134getWhite0d7_KjU(), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1853647012$lambda$6(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C454@21273L271:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1853647012, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1853647012.<anonymous> (MainActivity.kt:454)");
            }
            IconKt.m2520Iconww6aTOc(RefreshKt.getRefresh(Icons.INSTANCE.getDefault()), "Sync Now", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), ColorKt.Color(4279599165L), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1745189059$lambda$8(AnimatedContentScope AnimatedContent, ProfessionalWord wordItem, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        Intrinsics.checkNotNullParameter(wordItem, "wordItem");
        ComposerKt.sourceInformation($composer, "CN(wordItem)949@43797L834:MainActivity.kt#t7vdsr");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1745189059, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1745189059.<anonymous> (MainActivity.kt:949)");
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
        ComposerKt.sourceInformationMarkerStart($composer, -1723651521, "C952@43942L10,950@43830L249,957@44210L10,955@44104L244,962@44508L10,960@44373L236:MainActivity.kt#t7vdsr");
        TextKt.m3072TextNvy7gAk("WORD OF THE DAY :", null, ColorKt.Color(4278413729L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
        TextKt.m3072TextNvy7gAk(wordItem.getWord(), null, ColorKt.Color(4278995566L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
        TextKt.m3072TextNvy7gAk("Pronunciation: " + wordItem.getPronunciation(), null, ColorKt.Color(4278355143L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 384, 0, 131066);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2005326491$lambda$9(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C989@45728L296:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2005326491, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$2005326491.<anonymous> (MainActivity.kt:989)");
            }
            IconKt.m2520Iconww6aTOc(VolumeUpKt.getVolumeUp(Icons.AutoMirrored.Filled.INSTANCE), "Listen Pronunciation", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(28)), ColorKt.Color(4278355143L), $composer, 3504, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1164486689$lambda$11(AnimatedContentScope AnimatedContent, ProfessionalWord wordItem, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        Intrinsics.checkNotNullParameter(wordItem, "wordItem");
        ComposerKt.sourceInformation($composer, "CN(wordItem)1008@46420L619:MainActivity.kt#t7vdsr");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1164486689, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1164486689.<anonymous> (MainActivity.kt:1008)");
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
        ComposerKt.sourceInformationMarkerStart($composer, 2057006738, "C1011@46564L10,1009@46449L208,1017@46798L10,1015@46679L342:MainActivity.kt#t7vdsr");
        TextKt.m3072TextNvy7gAk("Meaning: " + wordItem.getMeaning(), null, ColorKt.Color(4281549141L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 384, 0, 131066);
        TextKt.m3072TextNvy7gAk("Example: \"" + wordItem.getExample() + "\"", PaddingKt.m823paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7905constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.Color(4278355143L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), 0L, 0L, null, FontStyle.m7437boximpl(FontStyle.INSTANCE.m7446getItalic_LCdwA()), null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777207, null), $composer, 432, 0, 131064);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1139491633$lambda$12(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C1038@47750L34:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1139491633, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1139491633.<anonymous> (MainActivity.kt:1038)");
            }
            TextKt.m3072TextNvy7gAk("Previous", null, 0L, null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1622931944$lambda$13(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C1047@48129L30:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1622931944, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1622931944.<anonymous> (MainActivity.kt:1047)");
            }
            TextKt.m3072TextNvy7gAk("Next", null, 0L, null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1861318462$lambda$14(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C1055@48416L86,1056@48523L39,1057@48583L43:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1861318462, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1861318462.<anonymous> (MainActivity.kt:1055)");
            }
            IconKt.m2520Iconww6aTOc(SearchKt.getSearch(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Search 1000 Words", null, 0L, null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1601726039$lambda$17(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C1081@49265L309:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1601726039, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1601726039.<anonymous> (MainActivity.kt:1081)");
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
            ComposerKt.sourceInformationMarkerStart($composer, 1099596552, "C1082@49354L10,1082@49290L122,1083@49496L10,1083@49540L11,1083@49429L131:MainActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("Professional 1000 Word Dictionary", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
            TextKt.m3072TextNvy7gAk("Tap the speaker icon to listen aloud", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
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

    static final Unit lambda_1908615437$lambda$18(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1092@49903L35:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1908615437, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1908615437.<anonymous> (MainActivity.kt:1092)");
            }
            TextKt.m3072TextNvy7gAk("Search words or meanings...", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1832776876$lambda$19(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1093@49982L53:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1832776876, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1832776876.<anonymous> (MainActivity.kt:1093)");
            }
            IconKt.m2520Iconww6aTOc(SearchKt.getSearch(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1109149940$lambda$20(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C1132@52553L11,1132@52460L113:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1109149940, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1109149940.<anonymous> (MainActivity.kt:1132)");
            }
            IconKt.m2520Iconww6aTOc(VolumeUpKt.getVolumeUp(Icons.AutoMirrored.Filled.INSTANCE), "Listen", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_625657411$lambda$15(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1150@53129L13:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(625657411, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$625657411.<anonymous> (MainActivity.kt:1150)");
            }
            TextKt.m3072TextNvy7gAk("Close", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_997702114$lambda$21(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1368@61891L48:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(997702114, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$997702114.<anonymous> (MainActivity.kt:1368)");
            }
            TextKt.m3072TextNvy7gAk("Enroll Now", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 262078);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1392927456$lambda$23(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C1572@69743L891:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1392927456, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1392927456.<anonymous> (MainActivity.kt:1572)");
            }
            Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(4));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM820paddingVpY3zN4);
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
            ComposerKt.sourceInformationMarkerStart($composer, -181885373, "C1580@70198L10,1576@69972L277,1582@70278L39,1585@70462L10,1583@70346L262:MainActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("G", null, ColorKt.Color(4282549748L), null, 0L, null, FontWeight.INSTANCE.getBlack(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), $composer, 1573254, 0, 131002);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Google Review", null, ColorKt.Color(4279983648L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
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

    static final Unit lambda__716414199$lambda$25(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C1597@70904L903:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-716414199, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-716414199.<anonymous> (MainActivity.kt:1597)");
            }
            Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(4));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM820paddingVpY3zN4);
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
            ComposerKt.sourceInformationMarkerStart($composer, 1759548974, "C1601@71133L282,1607@71444L39,1610@71636L10,1608@71512L269:MainActivity.kt#t7vdsr");
            IconKt.m2520Iconww6aTOc(StarKt.getStar(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14)), ColorKt.Color(4294948864L), $composer, 3504, 0);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(3)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Highest Rated (5.0 ★)", null, ColorKt.Color(4294278935L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
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

    static final Unit lambda_1483673058$lambda$26(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C1621@72100L10,1621@72055L68,1622@72144L39,1623@72204L195:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1483673058, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1483673058.<anonymous> (MainActivity.kt:1621)");
            }
            TextKt.m3072TextNvy7gAk("View on Google", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 6, 0, 131070);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(2)), $composer, 6);
            IconKt.m2520Iconww6aTOc(OpenInNewKt.getOpenInNew(Icons.AutoMirrored.Filled.INSTANCE), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14)), 0L, $composer, 432, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1696391455$lambda$28(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C1712@76024L840:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1696391455, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1696391455.<anonymous> (MainActivity.kt:1712)");
            }
            Modifier modifierM820paddingVpY3zN4 = PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(6), Dp.m7905constructorimpl(2));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM820paddingVpY3zN4);
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
            ComposerKt.sourceInformationMarkerStart($composer, 1759268841, "C1720@76447L10,1716@76237L257,1722@76519L39,1725@76705L10,1723@76583L259:MainActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("G", null, ColorKt.Color(4282549748L), null, 0L, null, FontWeight.INSTANCE.getBlack(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 1573254, 0, 131002);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Highest Rated Google Review", null, ColorKt.Color(4279599165L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
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

    static final Unit lambda__1248528462$lambda$29(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1874@82913L83,1875@83017L39,1876@83136L10,1876@83077L116:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1248528462, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1248528462.<anonymous> (MainActivity.kt:1874)");
            }
            IconKt.m2520Iconww6aTOc(MapKt.getMap(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(18)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Open Location in Google Maps", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1488165959$lambda$30(RowScope OutlinedButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation($composer, "C1954@86814L85,1955@86920L39,1956@86980L16:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1488165959, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1488165959.<anonymous> (MainActivity.kt:1954)");
            }
            IconKt.m2520Iconww6aTOc(PhoneKt.getPhone(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Call Now", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_500765577$lambda$31(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C1971@87643L85,1972@87749L39,1973@87809L16:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(500765577, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$500765577.<anonymous> (MainActivity.kt:1971)");
            }
            IconKt.m2520Iconww6aTOc(EmailKt.getEmail(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), 0L, $composer, 432, 8);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Email Us", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1803826126$lambda$34(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2016@89177L20:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1803826126, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1803826126.<anonymous> (MainActivity.kt:2016)");
            }
            TextKt.m3072TextNvy7gAk("Student Name", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_2137010551$lambda$35(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2024@89470L20:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2137010551, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$2137010551.<anonymous> (MainActivity.kt:2024)");
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

    static final Unit lambda_2002427862$lambda$36(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2032@89769L33:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2002427862, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$2002427862.<anonymous> (MainActivity.kt:2032)");
            }
            TextKt.m3072TextNvy7gAk("Interested Course / Query", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1737540461$lambda$32(RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C2047@90308L22:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1737540461, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1737540461.<anonymous> (MainActivity.kt:2047)");
            }
            TextKt.m3072TextNvy7gAk("Submit Inquiry", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1821505884$lambda$33(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2052@90444L14:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1821505884, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1821505884.<anonymous> (MainActivity.kt:2052)");
            }
            TextKt.m3072TextNvy7gAk("Cancel", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_274545934$lambda$37(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2194@95485L10:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(274545934, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$274545934.<anonymous> (MainActivity.kt:2194)");
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

    static final Unit lambda_762593040$lambda$38(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2197@95610L14:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(762593040, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$762593040.<anonymous> (MainActivity.kt:2197)");
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

    static final Unit lambda_2144619733$lambda$40(AnimatedContentScope AnimatedContent, boolean signUpMode, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        ComposerKt.sourceInformation($composer, "CN(signUpMode)2331@100899L819:MainActivity.kt#t7vdsr");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2144619733, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$2144619733.<anonymous> (MainActivity.kt:2331)");
        }
        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
        Alignment.Horizontal start = Alignment.INSTANCE.getStart();
        ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), start, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
        CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
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
        Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
        Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
        Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
        int i2 = (i >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        int i3 = ((390 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 1723683513, "C2337@101223L10,2335@101077L286,2342@101595L10,2340@101388L308:MainActivity.kt#t7vdsr");
        TextKt.m3072TextNvy7gAk(signUpMode ? "Create Account" : "Welcome Back!", null, ColorKt.Color(4279179050L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getHeadlineSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
        TextKt.m3072TextNvy7gAk(signUpMode ? "Please fill in your details to get started" : "Please login to continue your learning journey", null, ColorKt.Color(4284773515L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 384, 0, 131066);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1798851514$lambda$41(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2385@103741L16:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1798851514, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1798851514.<anonymous> (MainActivity.kt:2385)");
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

    static final Unit lambda_1107947545$lambda$42(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2386@103805L22:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1107947545, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1107947545.<anonymous> (MainActivity.kt:2386)");
            }
            TextKt.m3072TextNvy7gAk("Enter username", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_81595884$lambda$43(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2404@104661L21:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(81595884, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$81595884.<anonymous> (MainActivity.kt:2404)");
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

    static final Unit lambda_1427350091$lambda$44(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2405@104722L22:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1427350091, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1427350091.<anonymous> (MainActivity.kt:2405)");
            }
            TextKt.m3072TextNvy7gAk("user@gmail.com", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1521862998$lambda$45(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2407@104823L78:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1521862998, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1521862998.<anonymous> (MainActivity.kt:2407)");
            }
            IconKt.m2520Iconww6aTOc(EmailKt.getEmail(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4278355143L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1828687971$lambda$46(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2422@105593L16:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1828687971, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1828687971.<anonymous> (MainActivity.kt:2422)");
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

    static final Unit lambda__1981642494$lambda$47(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2423@105649L22:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1981642494, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1981642494.<anonymous> (MainActivity.kt:2423)");
            }
            TextKt.m3072TextNvy7gAk("Enter password", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1497005663$lambda$48(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2425@105838L77:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1497005663, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1497005663.<anonymous> (MainActivity.kt:2425)");
            }
            IconKt.m2520Iconww6aTOc(LockKt.getLock(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4278355143L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1451060559$lambda$49(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2457@107523L21:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1451060559, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-1451060559.<anonymous> (MainActivity.kt:2457)");
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

    static final Unit lambda_310156112$lambda$50(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2458@107592L18:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(310156112, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$310156112.<anonymous> (MainActivity.kt:2458)");
            }
            TextKt.m3072TextNvy7gAk("Select DOB", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1170402992$lambda$51(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2461@107777L82:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1170402992, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$1170402992.<anonymous> (MainActivity.kt:2461)");
            }
            IconKt.m2520Iconww6aTOc(DateRangeKt.getDateRange(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, ColorKt.Color(4278355143L), $composer, 3120, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__2045387321$lambda$52(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2486@109159L31:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2045387321, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-2045387321.<anonymous> (MainActivity.kt:2486)");
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

    static final Unit lambda_2102651110$lambda$53(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2487@109242L39:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2102651110, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$2102651110.<anonymous> (MainActivity.kt:2487)");
            }
            TextKt.m3072TextNvy7gAk("Select Class or Education Level", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__377962242$lambda$54(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2526@111415L36:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-377962242, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-377962242.<anonymous> (MainActivity.kt:2526)");
            }
            TextKt.m3072TextNvy7gAk("Competitive Exam Preparation", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__460594328$lambda$55(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2559@113223L19:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-460594328, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-460594328.<anonymous> (MainActivity.kt:2559)");
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

    static final Unit lambda__148488121$lambda$56(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2560@113290L25:MainActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-148488121, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-148488121.<anonymous> (MainActivity.kt:2560)");
            }
            TextKt.m3072TextNvy7gAk("Enter school name", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__979317426$lambda$57(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C2607@115721L10,2607@115674L95:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-979317426, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$-979317426.<anonymous> (MainActivity.kt:2607)");
            }
            TextKt.m3072TextNvy7gAk("Forgot Password?", null, ColorKt.Color(4278355143L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_510241912$lambda$58(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2794@126471L18:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(510241912, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$510241912.<anonymous> (MainActivity.kt:2794)");
            }
            MainActivityKt.AcademyAppScreen(null, $composer, 0, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_142826414$lambda$61(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C2811@127084L2,2802@126616L480:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(142826414, $changed, -1, "com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt.lambda$142826414.<anonymous> (MainActivity.kt:2802)");
            }
            TeacherProfile teacherProfile = new TeacherProfile("Suman Ghosh", "Lead English Teacher & Mentor", "M.A English | 30+ Years of Teaching Experience", 4.9d, 1500, null, null, CollectionsKt.listOf((Object[]) new String[]{"Spoken English", "Advanced Grammar & Literature", "IELTS & Public Speaking"}), 96, null);
            ComposerKt.sourceInformationMarkerStart($composer, -182754320, "CC(remember):MainActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ComposableSingletons$MainActivityKt$$ExternalSyntheticLambda53
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            MainActivityKt.TeacherProfileCard(teacherProfile, (Function0) objRememberedValue, $composer, TeacherProfile.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
