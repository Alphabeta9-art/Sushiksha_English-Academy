package com.example.sushikhasacademyapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.example.sushikhasacademyapp.ui.theme.ThemeKt;
import com.google.firebase.auth.FirebaseAuth;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/example/sushikhasacademyapp/ProfileActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProfileActivity extends ComponentActivity {
    public static final int $stable = 8;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String userEmail = getIntent().getStringExtra("USER_EMAIL");
        if (userEmail == null) {
            userEmail = LoginPrefs.INSTANCE.getUserEmail(this);
        }
        final boolean isAdmin = getIntent().getBooleanExtra("IS_ADMIN", LoginPrefs.INSTANCE.isAdmin(this));
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(585441265, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ProfileActivity.onCreate$lambda$6(userEmail, isAdmin, this, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$6(final String $userEmail, final boolean $isAdmin, final ProfileActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C143@5858L661,143@5833L686:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(585441265, $changed, -1, "com.example.sushikhasacademyapp.ProfileActivity.onCreate.<anonymous> (ProfileActivity.kt:143)");
            }
            ThemeKt.SushikhasAcademyAppTheme(false, false, ComposableLambdaKt.rememberComposableLambda(1826575631, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProfileActivity.onCreate$lambda$6$lambda$5($userEmail, $isAdmin, this$0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$6$lambda$5(String $userEmail, boolean $isAdmin, final ProfileActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C147@6002L12,148@6048L439,144@5876L629:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1826575631, $changed, -1, "com.example.sushikhasacademyapp.ProfileActivity.onCreate.<anonymous>.<anonymous> (ProfileActivity.kt:144)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1302438053, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance(this$0);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivity$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivity.onCreate$lambda$6$lambda$5$lambda$1$lambda$0(this.f$0);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            Function0 function0 = (Function0) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1302436154, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean zChangedInstance2 = $composer.changedInstance(this$0);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivity$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivity.onCreate$lambda$6$lambda$5$lambda$4$lambda$3(this.f$0);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ProfileActivityKt.ProfileScreen($userEmail, $isAdmin, function0, (Function0) objRememberedValue2, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$6$lambda$5$lambda$1$lambda$0(ProfileActivity this$0) {
        this$0.finish();
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$6$lambda$5$lambda$4$lambda$3(ProfileActivity this$0) {
        LoginPrefs.INSTANCE.clearLoginState(this$0);
        FirebaseAuth.getInstance().signOut();
        Intent mainIntent = new Intent(this$0, (Class<?>) MainActivity.class);
        mainIntent.setFlags(268468224);
        this$0.startActivity(mainIntent);
        this$0.finish();
        return Unit.INSTANCE;
    }
}
