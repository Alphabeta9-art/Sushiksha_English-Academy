package com.example.sushikhasacademyapp;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001a\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\fHÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/example/sushikhasacademyapp/QuestionModel;", "", "text", "", "isMcq", "", "optionA", "optionB", "optionC", "optionD", "correctAns", "marks", "", "<init>", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getText", "()Ljava/lang/String;", "()Z", "getOptionA", "getOptionB", "getOptionC", "getOptionD", "getCorrectAns", "getMarks", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", MetadataValidation.EQUALS, "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class QuestionModel {
    public static final int $stable = 0;
    private final String correctAns;
    private final boolean isMcq;
    private final int marks;
    private final String optionA;
    private final String optionB;
    private final String optionC;
    private final String optionD;
    private final String text;

    public QuestionModel() {
        this(null, false, null, null, null, null, null, 0, 255, null);
    }

    public static /* synthetic */ QuestionModel copy$default(QuestionModel questionModel, String str, boolean z, String str2, String str3, String str4, String str5, String str6, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = questionModel.text;
        }
        if ((i2 & 2) != 0) {
            z = questionModel.isMcq;
        }
        if ((i2 & 4) != 0) {
            str2 = questionModel.optionA;
        }
        if ((i2 & 8) != 0) {
            str3 = questionModel.optionB;
        }
        if ((i2 & 16) != 0) {
            str4 = questionModel.optionC;
        }
        if ((i2 & 32) != 0) {
            str5 = questionModel.optionD;
        }
        if ((i2 & 64) != 0) {
            str6 = questionModel.correctAns;
        }
        if ((i2 & 128) != 0) {
            i = questionModel.marks;
        }
        String str7 = str6;
        int i3 = i;
        String str8 = str4;
        String str9 = str5;
        return questionModel.copy(str, z, str2, str3, str8, str9, str7, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsMcq() {
        return this.isMcq;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOptionA() {
        return this.optionA;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOptionB() {
        return this.optionB;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getOptionC() {
        return this.optionC;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getOptionD() {
        return this.optionD;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCorrectAns() {
        return this.correctAns;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getMarks() {
        return this.marks;
    }

    public final QuestionModel copy(String text, boolean isMcq, String optionA, String optionB, String optionC, String optionD, String correctAns, int marks) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(optionA, "optionA");
        Intrinsics.checkNotNullParameter(optionB, "optionB");
        Intrinsics.checkNotNullParameter(optionC, "optionC");
        Intrinsics.checkNotNullParameter(optionD, "optionD");
        Intrinsics.checkNotNullParameter(correctAns, "correctAns");
        return new QuestionModel(text, isMcq, optionA, optionB, optionC, optionD, correctAns, marks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QuestionModel)) {
            return false;
        }
        QuestionModel questionModel = (QuestionModel) other;
        return Intrinsics.areEqual(this.text, questionModel.text) && this.isMcq == questionModel.isMcq && Intrinsics.areEqual(this.optionA, questionModel.optionA) && Intrinsics.areEqual(this.optionB, questionModel.optionB) && Intrinsics.areEqual(this.optionC, questionModel.optionC) && Intrinsics.areEqual(this.optionD, questionModel.optionD) && Intrinsics.areEqual(this.correctAns, questionModel.correctAns) && this.marks == questionModel.marks;
    }

    public int hashCode() {
        return (((((((((((((this.text.hashCode() * 31) + Boolean.hashCode(this.isMcq)) * 31) + this.optionA.hashCode()) * 31) + this.optionB.hashCode()) * 31) + this.optionC.hashCode()) * 31) + this.optionD.hashCode()) * 31) + this.correctAns.hashCode()) * 31) + Integer.hashCode(this.marks);
    }

    public String toString() {
        return "QuestionModel(text=" + this.text + ", isMcq=" + this.isMcq + ", optionA=" + this.optionA + ", optionB=" + this.optionB + ", optionC=" + this.optionC + ", optionD=" + this.optionD + ", correctAns=" + this.correctAns + ", marks=" + this.marks + ")";
    }

    public QuestionModel(String text, boolean isMcq, String optionA, String optionB, String optionC, String optionD, String correctAns, int marks) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(optionA, "optionA");
        Intrinsics.checkNotNullParameter(optionB, "optionB");
        Intrinsics.checkNotNullParameter(optionC, "optionC");
        Intrinsics.checkNotNullParameter(optionD, "optionD");
        Intrinsics.checkNotNullParameter(correctAns, "correctAns");
        this.text = text;
        this.isMcq = isMcq;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;
        this.marks = marks;
    }

    public /* synthetic */ QuestionModel(String str, boolean z, String str2, String str3, String str4, String str5, String str6, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? "Option A" : str6, (i2 & 128) != 0 ? 2 : i);
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
}
