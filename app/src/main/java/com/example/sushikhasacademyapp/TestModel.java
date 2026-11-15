package com.example.sushikhasacademyapp;

import androidx.core.app.NotificationCompat;
import com.cloudinary.metadata.MetadataValidation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0006HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u0089\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u0010.\u001a\u00020\u00062\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015¨\u00063"}, d2 = {"Lcom/example/sushikhasacademyapp/TestModel;", "", "id", "", "title", "isOnline", "", NotificationCompat.CATEGORY_STATUS, "isDone", "userScore", "questions", "", "Lcom/example/sushikhasacademyapp/QuestionModel;", "date", "time", "location", "syllabus", "instructions", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "()Z", "getStatus", "getUserScore", "getQuestions", "()Ljava/util/List;", "getDate", "getTime", "getLocation", "getSyllabus", "getInstructions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", MetadataValidation.EQUALS, "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TestModel {
    public static final int $stable = 8;
    private final String date;
    private final String id;
    private final String instructions;
    private final boolean isDone;
    private final boolean isOnline;
    private final String location;
    private final List<QuestionModel> questions;
    private final String status;
    private final String syllabus;
    private final String time;
    private final String title;
    private final String userScore;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TestModel copy$default(TestModel testModel, String str, String str2, boolean z, String str3, boolean z2, String str4, List list, String str5, String str6, String str7, String str8, String str9, int i, Object obj) {
        if ((i & 1) != 0) {
            str = testModel.id;
        }
        if ((i & 2) != 0) {
            str2 = testModel.title;
        }
        if ((i & 4) != 0) {
            z = testModel.isOnline;
        }
        if ((i & 8) != 0) {
            str3 = testModel.status;
        }
        if ((i & 16) != 0) {
            z2 = testModel.isDone;
        }
        if ((i & 32) != 0) {
            str4 = testModel.userScore;
        }
        if ((i & 64) != 0) {
            list = testModel.questions;
        }
        if ((i & 128) != 0) {
            str5 = testModel.date;
        }
        if ((i & 256) != 0) {
            str6 = testModel.time;
        }
        if ((i & 512) != 0) {
            str7 = testModel.location;
        }
        if ((i & 1024) != 0) {
            str8 = testModel.syllabus;
        }
        if ((i & 2048) != 0) {
            str9 = testModel.instructions;
        }
        String str10 = str8;
        String str11 = str9;
        String str12 = str6;
        String str13 = str7;
        List list2 = list;
        String str14 = str5;
        boolean z3 = z2;
        String str15 = str4;
        return testModel.copy(str, str2, z, str3, z3, str15, list2, str14, str12, str13, str10, str11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSyllabus() {
        return this.syllabus;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getInstructions() {
        return this.instructions;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsDone() {
        return this.isDone;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUserScore() {
        return this.userScore;
    }

    public final List<QuestionModel> component7() {
        return this.questions;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getTime() {
        return this.time;
    }

    public final TestModel copy(String id, String title, boolean isOnline, String status, boolean isDone, String userScore, List<QuestionModel> questions, String date, String time, String location, String syllabus, String instructions) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(questions, "questions");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(syllabus, "syllabus");
        Intrinsics.checkNotNullParameter(instructions, "instructions");
        return new TestModel(id, title, isOnline, status, isDone, userScore, questions, date, time, location, syllabus, instructions);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TestModel)) {
            return false;
        }
        TestModel testModel = (TestModel) other;
        return Intrinsics.areEqual(this.id, testModel.id) && Intrinsics.areEqual(this.title, testModel.title) && this.isOnline == testModel.isOnline && Intrinsics.areEqual(this.status, testModel.status) && this.isDone == testModel.isDone && Intrinsics.areEqual(this.userScore, testModel.userScore) && Intrinsics.areEqual(this.questions, testModel.questions) && Intrinsics.areEqual(this.date, testModel.date) && Intrinsics.areEqual(this.time, testModel.time) && Intrinsics.areEqual(this.location, testModel.location) && Intrinsics.areEqual(this.syllabus, testModel.syllabus) && Intrinsics.areEqual(this.instructions, testModel.instructions);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + Boolean.hashCode(this.isOnline)) * 31) + this.status.hashCode()) * 31) + Boolean.hashCode(this.isDone)) * 31) + (this.userScore == null ? 0 : this.userScore.hashCode())) * 31) + this.questions.hashCode()) * 31) + this.date.hashCode()) * 31) + this.time.hashCode()) * 31) + this.location.hashCode()) * 31) + this.syllabus.hashCode()) * 31) + this.instructions.hashCode();
    }

    public String toString() {
        return "TestModel(id=" + this.id + ", title=" + this.title + ", isOnline=" + this.isOnline + ", status=" + this.status + ", isDone=" + this.isDone + ", userScore=" + this.userScore + ", questions=" + this.questions + ", date=" + this.date + ", time=" + this.time + ", location=" + this.location + ", syllabus=" + this.syllabus + ", instructions=" + this.instructions + ")";
    }

    public TestModel(String id, String title, boolean isOnline, String status, boolean isDone, String userScore, List<QuestionModel> questions, String date, String time, String location, String syllabus, String instructions) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(questions, "questions");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(syllabus, "syllabus");
        Intrinsics.checkNotNullParameter(instructions, "instructions");
        this.id = id;
        this.title = title;
        this.isOnline = isOnline;
        this.status = status;
        this.isDone = isDone;
        this.userScore = userScore;
        this.questions = questions;
        this.date = date;
        this.time = time;
        this.location = location;
        this.syllabus = syllabus;
        this.instructions = instructions;
    }

    public /* synthetic */ TestModel(String str, String str2, boolean z, String str3, boolean z2, String str4, List list, String str5, String str6, String str7, String str8, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, str2, z, str3, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? CollectionsKt.emptyList() : list, (i & 128) != 0 ? "" : str5, (i & 256) != 0 ? "" : str6, (i & 512) != 0 ? "" : str7, (i & 1024) != 0 ? "" : str8, (i & 2048) != 0 ? "" : str9);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final String getStatus() {
        return this.status;
    }

    public final boolean isDone() {
        return this.isDone;
    }

    public final String getUserScore() {
        return this.userScore;
    }

    public final List<QuestionModel> getQuestions() {
        return this.questions;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getTime() {
        return this.time;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getSyllabus() {
        return this.syllabus;
    }

    public final String getInstructions() {
        return this.instructions;
    }
}
