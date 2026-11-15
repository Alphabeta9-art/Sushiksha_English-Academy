package com.example.sushikhasacademyapp.data;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalTestManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b*\b\u0087\b\u0018\u00002\u00020\u0001B\u007f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\rHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\rHÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u0081\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001J\u0013\u00103\u001a\u00020\u00062\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\rHÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010 R\u001a\u0010\u000f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010#R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010 ¨\u00067"}, d2 = {"Lcom/example/sushikhasacademyapp/data/ExtendedQuestion;", "", "id", "", "text", "isMcq", "", "optionA", "optionB", "optionC", "optionD", "correctAns", "marks", "", "studentAns", "teacherMarks", "teacherRemarks", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getText", "()Z", "getOptionA", "getOptionB", "getOptionC", "getOptionD", "getCorrectAns", "getMarks", "()I", "getStudentAns", "setStudentAns", "(Ljava/lang/String;)V", "getTeacherMarks", "setTeacherMarks", "(I)V", "getTeacherRemarks", "setTeacherRemarks", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", MetadataValidation.EQUALS, "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ExtendedQuestion {
    public static final int $stable = 8;
    private final String correctAns;
    private final String id;
    private final boolean isMcq;
    private final int marks;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private String studentAns;
    private int teacherMarks;
    private String teacherRemarks;
    private final String text;

    public ExtendedQuestion() {
        this(null, null, false, null, null, null, null, null, 0, null, 0, null, 4095, null);
    }

    public static /* synthetic */ ExtendedQuestion copy$default(ExtendedQuestion extendedQuestion, String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, int i, String str8, int i2, String str9, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = extendedQuestion.id;
        }
        if ((i3 & 2) != 0) {
            str2 = extendedQuestion.text;
        }
        if ((i3 & 4) != 0) {
            z = extendedQuestion.isMcq;
        }
        if ((i3 & 8) != 0) {
            str3 = extendedQuestion.optionA;
        }
        if ((i3 & 16) != 0) {
            str4 = extendedQuestion.optionB;
        }
        if ((i3 & 32) != 0) {
            str5 = extendedQuestion.optionC;
        }
        if ((i3 & 64) != 0) {
            str6 = extendedQuestion.optionD;
        }
        if ((i3 & 128) != 0) {
            str7 = extendedQuestion.correctAns;
        }
        if ((i3 & 256) != 0) {
            i = extendedQuestion.marks;
        }
        if ((i3 & 512) != 0) {
            str8 = extendedQuestion.studentAns;
        }
        if ((i3 & 1024) != 0) {
            i2 = extendedQuestion.teacherMarks;
        }
        if ((i3 & 2048) != 0) {
            str9 = extendedQuestion.teacherRemarks;
        }
        int i4 = i2;
        String str10 = str9;
        int i5 = i;
        String str11 = str8;
        String str12 = str6;
        String str13 = str7;
        String str14 = str4;
        String str15 = str5;
        return extendedQuestion.copy(str, str2, z, str3, str14, str15, str12, str13, i5, str11, i4, str10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getStudentAns() {
        return this.studentAns;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getTeacherMarks() {
        return this.teacherMarks;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTeacherRemarks() {
        return this.teacherRemarks;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsMcq() {
        return this.isMcq;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOptionA() {
        return this.optionA;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOptionB() {
        return this.optionB;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getOptionC() {
        return this.optionC;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getOptionD() {
        return this.optionD;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCorrectAns() {
        return this.correctAns;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getMarks() {
        return this.marks;
    }

    public final ExtendedQuestion copy(String id, String text, boolean isMcq, String optionA, String optionB, String optionC, String optionD, String correctAns, int marks, String studentAns, int teacherMarks, String teacherRemarks) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(optionA, "optionA");
        Intrinsics.checkNotNullParameter(optionB, "optionB");
        Intrinsics.checkNotNullParameter(optionC, "optionC");
        Intrinsics.checkNotNullParameter(optionD, "optionD");
        Intrinsics.checkNotNullParameter(correctAns, "correctAns");
        Intrinsics.checkNotNullParameter(studentAns, "studentAns");
        Intrinsics.checkNotNullParameter(teacherRemarks, "teacherRemarks");
        return new ExtendedQuestion(id, text, isMcq, optionA, optionB, optionC, optionD, correctAns, marks, studentAns, teacherMarks, teacherRemarks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExtendedQuestion)) {
            return false;
        }
        ExtendedQuestion extendedQuestion = (ExtendedQuestion) other;
        return Intrinsics.areEqual(this.id, extendedQuestion.id) && Intrinsics.areEqual(this.text, extendedQuestion.text) && this.isMcq == extendedQuestion.isMcq && Intrinsics.areEqual(this.optionA, extendedQuestion.optionA) && Intrinsics.areEqual(this.optionB, extendedQuestion.optionB) && Intrinsics.areEqual(this.optionC, extendedQuestion.optionC) && Intrinsics.areEqual(this.optionD, extendedQuestion.optionD) && Intrinsics.areEqual(this.correctAns, extendedQuestion.correctAns) && this.marks == extendedQuestion.marks && Intrinsics.areEqual(this.studentAns, extendedQuestion.studentAns) && this.teacherMarks == extendedQuestion.teacherMarks && Intrinsics.areEqual(this.teacherRemarks, extendedQuestion.teacherRemarks);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.text.hashCode()) * 31) + Boolean.hashCode(this.isMcq)) * 31) + this.optionA.hashCode()) * 31) + this.optionB.hashCode()) * 31) + this.optionC.hashCode()) * 31) + this.optionD.hashCode()) * 31) + this.correctAns.hashCode()) * 31) + Integer.hashCode(this.marks)) * 31) + this.studentAns.hashCode()) * 31) + Integer.hashCode(this.teacherMarks)) * 31) + this.teacherRemarks.hashCode();
    }

    public String toString() {
        return "ExtendedQuestion(id=" + this.id + ", text=" + this.text + ", isMcq=" + this.isMcq + ", optionA=" + this.optionA + ", optionB=" + this.optionB + ", optionC=" + this.optionC + ", optionD=" + this.optionD + ", correctAns=" + this.correctAns + ", marks=" + this.marks + ", studentAns=" + this.studentAns + ", teacherMarks=" + this.teacherMarks + ", teacherRemarks=" + this.teacherRemarks + ")";
    }

    public ExtendedQuestion(String id, String text, boolean isMcq, String optionA, String optionB, String optionC, String optionD, String correctAns, int marks, String studentAns, int teacherMarks, String teacherRemarks) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(optionA, "optionA");
        Intrinsics.checkNotNullParameter(optionB, "optionB");
        Intrinsics.checkNotNullParameter(optionC, "optionC");
        Intrinsics.checkNotNullParameter(optionD, "optionD");
        Intrinsics.checkNotNullParameter(correctAns, "correctAns");
        Intrinsics.checkNotNullParameter(studentAns, "studentAns");
        Intrinsics.checkNotNullParameter(teacherRemarks, "teacherRemarks");
        this.id = id;
        this.text = text;
        this.isMcq = isMcq;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;
        this.marks = marks;
        this.studentAns = studentAns;
        this.teacherMarks = teacherMarks;
        this.teacherRemarks = teacherRemarks;
    }

    public /* synthetic */ ExtendedQuestion(String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, int i, String str8, int i2, String str9, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? true : z, (i3 & 8) != 0 ? "" : str3, (i3 & 16) != 0 ? "" : str4, (i3 & 32) != 0 ? "" : str5, (i3 & 64) != 0 ? "" : str6, (i3 & 128) != 0 ? "Option A" : str7, (i3 & 256) != 0 ? 5 : i, (i3 & 512) != 0 ? "" : str8, (i3 & 1024) != 0 ? 0 : i2, (i3 & 2048) != 0 ? "" : str9);
    }

    public final String getId() {
        return this.id;
    }

    public final String getText() {
        return this.text;
    }

    public final boolean isMcq() {
        return this.isMcq;
    }

    public final String getOptionA() {
        return this.optionA;
    }

    public final String getOptionB() {
        return this.optionB;
    }

    public final String getOptionC() {
        return this.optionC;
    }

    public final String getOptionD() {
        return this.optionD;
    }

    public final String getCorrectAns() {
        return this.correctAns;
    }

    public final int getMarks() {
        return this.marks;
    }

    public final String getStudentAns() {
        return this.studentAns;
    }

    public final void setStudentAns(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.studentAns = str;
    }

    public final int getTeacherMarks() {
        return this.teacherMarks;
    }

    public final void setTeacherMarks(int i) {
        this.teacherMarks = i;
    }

    public final String getTeacherRemarks() {
        return this.teacherRemarks;
    }

    public final void setTeacherRemarks(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.teacherRemarks = str;
    }
}
