package com.example.sushikhasacademyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.autofill.HintConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005J\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00162\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/example/sushikhasacademyapp/ProfilePrefs;", "", "<init>", "()V", "PREF_NAME", "", "KEY_PROFILE_PATH", "KEY_USERNAME", "KEY_DOB", "KEY_CLASS", "KEY_SCHOOL", "KEY_EXAM", "saveUserDetails", "", "context", "Landroid/content/Context;", HintConstants.AUTOFILL_HINT_USERNAME, "dob", "studentClass", "school", "exam", "getUserDetails", "", "saveProfileImage", "sourceUri", "Landroid/net/Uri;", "getProfileImagePath", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProfilePrefs {
    public static final int $stable = 0;
    public static final ProfilePrefs INSTANCE = new ProfilePrefs();
    private static final String KEY_CLASS = "profile_class";
    private static final String KEY_DOB = "profile_dob";
    private static final String KEY_EXAM = "profile_exam";
    private static final String KEY_PROFILE_PATH = "profile_image_path";
    private static final String KEY_SCHOOL = "profile_school";
    private static final String KEY_USERNAME = "profile_username";
    private static final String PREF_NAME = "sushiksha_profile_prefs";

    private ProfilePrefs() {
    }

    public final void saveUserDetails(Context context, String username, String dob, String studentClass, String school, String exam) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(dob, "dob");
        Intrinsics.checkNotNullParameter(studentClass, "studentClass");
        Intrinsics.checkNotNullParameter(school, "school");
        Intrinsics.checkNotNullParameter(exam, "exam");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        prefs.edit().putString(KEY_USERNAME, username).putString(KEY_DOB, dob).putString(KEY_CLASS, studentClass).putString(KEY_SCHOOL, school).putString(KEY_EXAM, exam).apply();
    }

    public final Map<String, String> getUserDetails(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        Pair[] pairArr = new Pair[5];
        String string = prefs.getString(KEY_USERNAME, "");
        if (string == null) {
            string = "";
        }
        pairArr[0] = TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, string);
        String string2 = prefs.getString(KEY_DOB, "");
        if (string2 == null) {
            string2 = "";
        }
        pairArr[1] = TuplesKt.to("dob", string2);
        String string3 = prefs.getString(KEY_CLASS, "");
        if (string3 == null) {
            string3 = "";
        }
        pairArr[2] = TuplesKt.to("class", string3);
        String string4 = prefs.getString(KEY_SCHOOL, "");
        if (string4 == null) {
            string4 = "";
        }
        pairArr[3] = TuplesKt.to("school", string4);
        String string5 = prefs.getString(KEY_EXAM, "");
        pairArr[4] = TuplesKt.to("exam", string5 != null ? string5 : "");
        return MapsKt.mapOf(pairArr);
    }

    public final String saveProfileImage(Context context, Uri sourceUri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sourceUri, "sourceUri");
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(sourceUri);
            File file = new File(context.getFilesDir(), "profile_pic.jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            if (inputStream != null) {
                ByteStreamsKt.copyTo$default(inputStream, outputStream, 0, 2, null);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            outputStream.close();
            String path = file.getAbsolutePath();
            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
            prefs.edit().putString(KEY_PROFILE_PATH, path).apply();
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    public final String getProfileImagePath(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, 0);
        String path = prefs.getString(KEY_PROFILE_PATH, null);
        if (path == null || !new File(path).exists()) {
            return null;
        }
        return path;
    }
}
