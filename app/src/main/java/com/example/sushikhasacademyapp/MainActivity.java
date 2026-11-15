package com.example.sushikhasacademyapp;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.example.sushikhasacademyapp.ui.theme.ThemeKt;
import com.example.sushikhasacademyapp.utils.AndroidTextToSpeechHelper;
import com.example.sushikhasacademyapp.utils.TextToSpeechHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\n\u001a\u00020\u0007H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/example/sushikhasacademyapp/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "ttsHelper", "Lcom/example/sushikhasacademyapp/utils/TextToSpeechHelper;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;
    private TextToSpeechHelper ttsHelper;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable$default(this, null, null, 3, null);
        this.ttsHelper = new AndroidTextToSpeechHelper(this);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(1338504979, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$1(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$1(final MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C80@3660L71,80@3635L96:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1338504979, $changed, -1, "com.example.sushikhasacademyapp.MainActivity.onCreate.<anonymous> (MainActivity.kt:80)");
            }
            ThemeKt.SushikhasAcademyAppTheme(false, false, ComposableLambdaKt.rememberComposableLambda(1516231345, true, new Function2() { // from class: com.example.sushikhasacademyapp.MainActivity$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$1$lambda$0(this.f$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$1$lambda$0(MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C81@3678L39:MainActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1516231345, $changed, -1, "com.example.sushikhasacademyapp.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:81)");
            }
            TextToSpeechHelper textToSpeechHelper = this$0.ttsHelper;
            if (textToSpeechHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsHelper");
                textToSpeechHelper = null;
            }
            MainActivityKt.AcademyAppScreen(textToSpeechHelper, $composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        TextToSpeechHelper textToSpeechHelper = this.ttsHelper;
        if (textToSpeechHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsHelper");
            textToSpeechHelper = null;
        }
        textToSpeechHelper.shutdown();
    }
}
