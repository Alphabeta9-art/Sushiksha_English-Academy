package com.example.sushikhasacademyapp;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/sushikhasacademyapp/LoginPrefs;", "", "<init>", "()V", "PREF_NAME", "", "KEY_IS_LOGGED_IN", "KEY_IS_ADMIN", "KEY_USER_EMAIL", "saveLoginState", "", "context", "Landroid/content/Context;", "isLoggedIn", "", "isAdmin", "email", "getUserEmail", "clearLoginState", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginPrefs {
    public static final int $stable = 0;
    public static final LoginPrefs INSTANCE = new LoginPrefs();
    private static final String KEY_IS_ADMIN = "is_admin";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String PREF_NAME = "sushiksha_login_prefs";

    private LoginPrefs() {
    }

    public final void saveLoginState(Context context, boolean isLoggedIn, boolean isAdmin, String email) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(email, "email");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).putBoolean(KEY_IS_ADMIN, isAdmin).putString(KEY_USER_EMAIL, email).apply();
    }

    public final boolean isLoggedIn(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public final boolean isAdmin(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        return prefs.getBoolean(KEY_IS_ADMIN, false);
    }

    public final String getUserEmail(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        String string = prefs.getString(KEY_USER_EMAIL, "");
        return string != null ? string : "";
    }

    public final void clearLoginState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        prefs.edit().clear().apply();
    }
}
