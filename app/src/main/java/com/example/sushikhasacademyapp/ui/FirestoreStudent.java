package com.example.sushikhasacademyapp.ui;

import androidx.autofill.HintConstants;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdminDashboardDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/example/sushikhasacademyapp/ui/FirestoreStudent;", "", "id", "", HintConstants.AUTOFILL_HINT_USERNAME, "email", "classLevel", "school", "exam", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getUsername", "getEmail", "getClassLevel", "getSchool", "getExam", "component1", "component2", "component3", "component4", "component5", "component6", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class FirestoreStudent {
    public static final int $stable = 0;
    private final String classLevel;
    private final String email;
    private final String exam;
    private final String id;
    private final String school;
    private final String username;

    public FirestoreStudent() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ FirestoreStudent copy$default(FirestoreStudent firestoreStudent, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = firestoreStudent.id;
        }
        if ((i & 2) != 0) {
            str2 = firestoreStudent.username;
        }
        if ((i & 4) != 0) {
            str3 = firestoreStudent.email;
        }
        if ((i & 8) != 0) {
            str4 = firestoreStudent.classLevel;
        }
        if ((i & 16) != 0) {
            str5 = firestoreStudent.school;
        }
        if ((i & 32) != 0) {
            str6 = firestoreStudent.exam;
        }
        String str7 = str5;
        String str8 = str6;
        return firestoreStudent.copy(str, str2, str3, str4, str7, str8);
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
    public final String getExam() {
        return this.exam;
    }

    public final FirestoreStudent copy(String id, String username, String email, String classLevel, String school, String exam) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(classLevel, "classLevel");
        Intrinsics.checkNotNullParameter(school, "school");
        Intrinsics.checkNotNullParameter(exam, "exam");
        return new FirestoreStudent(id, username, email, classLevel, school, exam);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirestoreStudent)) {
            return false;
        }
        FirestoreStudent firestoreStudent = (FirestoreStudent) other;
        return Intrinsics.areEqual(this.id, firestoreStudent.id) && Intrinsics.areEqual(this.username, firestoreStudent.username) && Intrinsics.areEqual(this.email, firestoreStudent.email) && Intrinsics.areEqual(this.classLevel, firestoreStudent.classLevel) && Intrinsics.areEqual(this.school, firestoreStudent.school) && Intrinsics.areEqual(this.exam, firestoreStudent.exam);
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + this.username.hashCode()) * 31) + this.email.hashCode()) * 31) + this.classLevel.hashCode()) * 31) + this.school.hashCode()) * 31) + this.exam.hashCode();
    }

    public String toString() {
        return "FirestoreStudent(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", classLevel=" + this.classLevel + ", school=" + this.school + ", exam=" + this.exam + ")";
    }

    public FirestoreStudent(String id, String username, String email, String classLevel, String school, String exam) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(classLevel, "classLevel");
        Intrinsics.checkNotNullParameter(school, "school");
        Intrinsics.checkNotNullParameter(exam, "exam");
        this.id = id;
        this.username = username;
        this.email = email;
        this.classLevel = classLevel;
        this.school = school;
        this.exam = exam;
    }

    public /* synthetic */ FirestoreStudent(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6);
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

    public final String getExam() {
        return this.exam;
    }
}
