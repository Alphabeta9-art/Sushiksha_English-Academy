package com.example.sushikhasacademyapp;

import android.content.Context;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.example.sushikhasacademyapp.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AdminStudentDetailsActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/example/sushikhasacademyapp/AdminStudentDetailsActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AdminStudentDetailsActivity extends ComponentActivity {
    public static final int $stable = 8;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String batchTitle = getIntent().getStringExtra("BATCH_TITLE");
        if (batchTitle == null) {
            batchTitle = "";
        }
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(1404427345, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return AdminStudentDetailsActivity.onCreate$lambda$4(batchTitle, this, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$4(final String $batchTitle, final AdminStudentDetailsActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C32@1450L257,32@1425L282:AdminStudentDetailsActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1404427345, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsActivity.onCreate.<anonymous> (AdminStudentDetailsActivity.kt:32)");
            }
            ThemeKt.SushikhasAcademyAppTheme(false, false, ComposableLambdaKt.rememberComposableLambda(1296701295, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivity.onCreate$lambda$4$lambda$3($batchTitle, this$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$4$lambda$3(String $batchTitle, final AdminStudentDetailsActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C33@1504L7:AdminStudentDetailsActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1296701295, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsActivity.onCreate.<anonymous>.<anonymous> (AdminStudentDetailsActivity.kt:33)");
            }
            LoginPrefs loginPrefs = LoginPrefs.INSTANCE;
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (loginPrefs.isAdmin((Context) objConsume)) {
                $composer.startReplaceGroup(1092485711);
                ComposerKt.sourceInformation($composer, "34@1583L12,34@1536L60");
                ComposerKt.sourceInformationMarkerStart($composer, 589432923, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance(this$0);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivity$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminStudentDetailsActivity.onCreate$lambda$4$lambda$3$lambda$1$lambda$0(this.f$0);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AdminStudentDetailsActivityKt.AdminStudentDetailsScreen($batchTitle, (Function0) objRememberedValue, $composer, 0);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1092590026);
                ComposerKt.sourceInformation($composer, "36@1663L12,36@1642L33");
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 589435483, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
                boolean zChangedInstance2 = $composer.changedInstance(this$0);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = (Function2) new AdminStudentDetailsActivity$onCreate$1$1$2$1(this$0, null);
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, $composer, 6);
                $composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$4$lambda$3$lambda$1$lambda$0(AdminStudentDetailsActivity this$0) {
        this$0.finish();
        return Unit.INSTANCE;
    }
}
