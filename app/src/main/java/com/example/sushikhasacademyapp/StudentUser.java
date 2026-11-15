package com.example.sushikhasacademyapp;

import androidx.autofill.HintConstants;
import androidx.core.app.FrameMetricsAggregator;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u000bHÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003Jc\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018¨\u0006*"}, d2 = {"Lcom/example/sushikhasacademyapp/StudentUser;", "", "id", "", HintConstants.AUTOFILL_HINT_USERNAME, "email", "classLevel", "school", "dob", "exam", "attendedDays", "", "totalWorkingDays", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getId", "()Ljava/lang/String;", "getUsername", "getEmail", "getClassLevel", "getSchool", "getDob", "getExam", "getAttendedDays", "()J", "getTotalWorkingDays", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StudentUser {
    public static final int $stable = 0;
    private final long attendedDays;
    private final String classLevel;
    private final String dob;
    private final String email;
    private final String exam;
    private final String id;
    private final String school;
    private final long totalWorkingDays;
    private final String username;

    public StudentUser() {
        this(null, null, null, null, null, null, null, 0L, 0L, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public static /* synthetic */ StudentUser copy$default(StudentUser studentUser, String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = studentUser.id;
        }
        if ((i & 2) != 0) {
            str2 = studentUser.username;
        }
        if ((i & 4) != 0) {
            str3 = studentUser.email;
        }
        if ((i & 8) != 0) {
            str4 = studentUser.classLevel;
        }
        if ((i & 16) != 0) {
            str5 = studentUser.school;
        }
        if ((i & 32) != 0) {
            str6 = studentUser.dob;
        }
        if ((i & 64) != 0) {
            str7 = studentUser.exam;
        }
        if ((i & 128) != 0) {
            j = studentUser.attendedDays;
        }
        if ((i & 256) != 0) {
            j2 = studentUser.totalWorkingDays;
        }
        long j3 = j2;
        long j4 = j;
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        String str11 = str3;
        return studentUser.copy(str, str2, str11, str4, str10, str8, str9, j4, j3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getClassLevel() {
        return this.classLevel;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSchool() {
        return this.school;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getDob() {
        return this.dob;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getExam() {
        return this.exam;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getAttendedDays() {
        return this.attendedDays;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getTotalWorkingDays() {
        return this.totalWorkingDays;
    }

    public final StudentUser copy(String id, String username, String email, String classLevel, String school, String dob, String exam, long attendedDays, long totalWorkingDays) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(classLevel, "classLevel");
        Intrinsics.checkNotNullParameter(school, "school");
        Intrinsics.checkNotNullParameter(dob, "dob");
        Intrinsics.checkNotNullParameter(exam, "exam");
        return new StudentUser(id, username, email, classLevel, school, dob, exam, attendedDays, totalWorkingDays);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StudentUser)) {
            return false;
        }
        StudentUser studentUser = (StudentUser) other;
        return Intrinsics.areEqual(this.id, studentUser.id) && Intrinsics.areEqual(this.username, studentUser.username) && Intrinsics.areEqual(this.email, studentUser.email) && Intrinsics.areEqual(this.classLevel, studentUser.classLevel) && Intrinsics.areEqual(this.school, studentUser.school) && Intrinsics.areEqual(this.dob, studentUser.dob) && Intrinsics.areEqual(this.exam, studentUser.exam) && this.attendedDays == studentUser.attendedDays && this.totalWorkingDays == studentUser.totalWorkingDays;
    }

    public int hashCode() {
        return (((((((((((((((this.id.hashCode() * 31) + this.username.hashCode()) * 31) + this.email.hashCode()) * 31) + this.classLevel.hashCode()) * 31) + this.school.hashCode()) * 31) + this.dob.hashCode()) * 31) + this.exam.hashCode()) * 31) + Long.hashCode(this.attendedDays)) * 31) + Long.hashCode(this.totalWorkingDays);
    }

    public String toString() {
        return "StudentUser(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", classLevel=" + this.classLevel + ", school=" + this.school + ", dob=" + this.dob + ", exam=" + this.exam + ", attendedDays=" + this.attendedDays + ", totalWorkingDays=" + this.totalWorkingDays + ")";
    }

    public StudentUser(String id, String username, String email, String classLevel, String school, String dob, String exam, long attendedDays, long totalWorkingDays) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(classLevel, "classLevel");
        Intrinsics.checkNotNullParameter(school, "school");
        Intrinsics.checkNotNullParameter(dob, "dob");
        Intrinsics.checkNotNullParameter(exam, "exam");
        this.id = id;
        this.username = username;
        this.email = email;
        this.classLevel = classLevel;
        this.school = school;
        this.dob = dob;
        this.exam = exam;
        this.attendedDays = attendedDays;
        this.totalWorkingDays = totalWorkingDays;
    }

    public /* synthetic */ StudentUser(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7, (i & 128) != 0 ? 24L : j, (i & 256) != 0 ? 30L : j2);
    }

    public final String getId() {
        return this.id;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getClassLevel() {
        return this.classLevel;
    }

    public final String getSchool() {
        return this.school;
    }

    public final String getDob() {
        return this.dob;
    }

    public final String getExam() {
        return this.exam;
    }

    public final long getAttendedDays() {
        return this.attendedDays;
    }

    public final long getTotalWorkingDays() {
        return this.totalWorkingDays;
    }
}
