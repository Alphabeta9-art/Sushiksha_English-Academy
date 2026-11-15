package com.example.sushikhasacademyapp;

import android.content.Context;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.example.sushikhasacademyapp.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/example/sushikhasacademyapp/BatchDetailActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BatchDetailActivity extends ComponentActivity {
    public static final int $stable = 8;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String batchTitle = getIntent().getStringExtra("BATCH_TITLE");
        if (batchTitle == null) {
            batchTitle = "Morning English Speaking Batch";
        }
        final String batchTiming = getIntent().getStringExtra("BATCH_TIMING");
        if (batchTiming == null) {
            batchTiming = "8:30 AM - 10:00 AM (Mon, Sat)";
        }
        final boolean isAdminIntent = getIntent().getBooleanExtra("IS_ADMIN", false);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-454556111, true, new Function2() { // from class: com.example.sushikhasacademyapp.BatchDetailActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return BatchDetailActivity.onCreate$lambda$3(isAdminIntent, batchTitle, batchTiming, this, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$3(boolean $isAdminIntent, final String $batchTitle, final String $batchTiming, final BatchDetailActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C63@2951L7,66@3077L250,66@3052L275:BatchDetailActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-454556111, $changed, -1, "com.example.sushikhasacademyapp.BatchDetailActivity.onCreate.<anonymous> (BatchDetailActivity.kt:63)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer);
            Context context = (Context) objConsume;
            final boolean effectiveIsAdmin = $isAdminIntent || LoginPrefs.INSTANCE.isAdmin(context);
            ThemeKt.SushikhasAcademyAppTheme(false, false, ComposableLambdaKt.rememberComposableLambda(534920527, true, new Function2() { // from class: com.example.sushikhasacademyapp.BatchDetailActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BatchDetailActivity.onCreate$lambda$3$lambda$2($batchTitle, $batchTiming, effectiveIsAdmin, this$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$3$lambda$2(String $batchTitle, String $batchTiming, boolean $effectiveIsAdmin, final BatchDetailActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C71@3283L12,67@3095L218:BatchDetailActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(534920527, $changed, -1, "com.example.sushikhasacademyapp.BatchDetailActivity.onCreate.<anonymous>.<anonymous> (BatchDetailActivity.kt:67)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1337442469, "CC(remember):BatchDetailActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance(this$0);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.BatchDetailActivity$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BatchDetailActivity.onCreate$lambda$3$lambda$2$lambda$1$lambda$0(this.f$0);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            BatchDetailActivityKt.BatchDetailScreen($batchTitle, $batchTiming, $effectiveIsAdmin, (Function0) objRememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$3$lambda$2$lambda$1$lambda$0(BatchDetailActivity this$0) {
        this$0.finish();
        return Unit.INSTANCE;
    }
}
