package com.example.sushikhasacademyapp.data;

import androidx.core.app.NotificationCompat;
import com.cloudinary.metadata.MetadataValidation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalTestManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b(\b\u0087\b\u0018\u00002\u00020\u0001B\u0099\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0015HÆ\u0003J\u009b\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0015HÆ\u0001J\u0013\u00109\u001a\u00020\u00072\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\tHÖ\u0001J\t\u0010<\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u001cR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006="}, d2 = {"Lcom/example/sushikhasacademyapp/data/FullTestModel;", "", "id", "", "title", "batchTitle", "isOnline", "", "durationMins", "", "totalMaxMarks", NotificationCompat.CATEGORY_STATUS, "questions", "", "Lcom/example/sushikhasacademyapp/data/ExtendedQuestion;", "date", "time", "location", "syllabus", "instructions", "createdAt", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getTitle", "getBatchTitle", "()Z", "getDurationMins", "()I", "getTotalMaxMarks", "getStatus", "getQuestions", "()Ljava/util/List;", "getDate", "getTime", "getLocation", "getSyllabus", "getInstructions", "getCreatedAt", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", MetadataValidation.EQUALS, "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class FullTestModel {
    public static final int $stable = 8;
    private final String batchTitle;
    private final long createdAt;
    private final String date;
    private final int durationMins;
    private final String id;
    private final String instructions;
    private final boolean isOnline;
    private final String location;
    private final List<ExtendedQuestion> questions;
    private final String status;
    private final String syllabus;
    private final String time;
    private final String title;
    private final int totalMaxMarks;

    public FullTestModel() {
        this(null, null, null, false, 0, 0, null, null, null, null, null, null, null, 0L, 16383, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FullTestModel copy$default(FullTestModel fullTestModel, String str, String str2, String str3, boolean z, int i, int i2, String str4, List list, String str5, String str6, String str7, String str8, String str9, long j, int i3, Object obj) {
        long j2;
        String str10;
        String str11 = (i3 & 1) != 0 ? fullTestModel.id : str;
        String str12 = (i3 & 2) != 0 ? fullTestModel.title : str2;
        String str13 = (i3 & 4) != 0 ? fullTestModel.batchTitle : str3;
        boolean z2 = (i3 & 8) != 0 ? fullTestModel.isOnline : z;
        int i4 = (i3 & 16) != 0 ? fullTestModel.durationMins : i;
        int i5 = (i3 & 32) != 0 ? fullTestModel.totalMaxMarks : i2;
        String str14 = (i3 & 64) != 0 ? fullTestModel.status : str4;
        List list2 = (i3 & 128) != 0 ? fullTestModel.questions : list;
        String str15 = (i3 & 256) != 0 ? fullTestModel.date : str5;
        String str16 = (i3 & 512) != 0 ? fullTestModel.time : str6;
        String str17 = (i3 & 1024) != 0 ? fullTestModel.location : str7;
        String str18 = (i3 & 2048) != 0 ? fullTestModel.syllabus : str8;
        String str19 = (i3 & 4096) != 0 ? fullTestModel.instructions : str9;
        if ((i3 & 8192) != 0) {
            str10 = str11;
            j2 = fullTestModel.createdAt;
        } else {
            j2 = j;
            str10 = str11;
        }
        return fullTestModel.copy(str10, str12, str13, z2, i4, i5, str14, list2, str15, str16, str17, str18, str19, j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getTime() {
        return this.time;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSyllabus() {
        return this.syllabus;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getInstructions() {
        return this.instructions;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBatchTitle() {
        return this.batchTitle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getDurationMins() {
        return this.durationMins;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getTotalMaxMarks() {
        return this.totalMaxMarks;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    public final List<ExtendedQuestion> component8() {
        return this.questions;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    public final FullTestModel copy(String id, String title, String batchTitle, boolean isOnline, int durationMins, int totalMaxMarks, String status, List<ExtendedQuestion> questions, String date, String time, String location, String syllabus, String instructions, long createdAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(questions, "questions");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(syllabus, "syllabus");
        Intrinsics.checkNotNullParameter(instructions, "instructions");
        return new FullTestModel(id, title, batchTitle, isOnline, durationMins, totalMaxMarks, status, questions, date, time, location, syllabus, instructions, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FullTestModel)) {
            return false;
        }
        FullTestModel fullTestModel = (FullTestModel) other;
        return Intrinsics.areEqual(this.id, fullTestModel.id) && Intrinsics.areEqual(this.title, fullTestModel.title) && Intrinsics.areEqual(this.batchTitle, fullTestModel.batchTitle) && this.isOnline == fullTestModel.isOnline && this.durationMins == fullTestModel.durationMins && this.totalMaxMarks == fullTestModel.totalMaxMarks && Intrinsics.areEqual(this.status, fullTestModel.status) && Intrinsics.areEqual(this.questions, fullTestModel.questions) && Intrinsics.areEqual(this.date, fullTestModel.date) && Intrinsics.areEqual(this.time, fullTestModel.time) && Intrinsics.areEqual(this.location, fullTestModel.location) && Intrinsics.areEqual(this.syllabus, fullTestModel.syllabus) && Intrinsics.areEqual(this.instructions, fullTestModel.instructions) && this.createdAt == fullTestModel.createdAt;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.batchTitle.hashCode()) * 31) + Boolean.hashCode(this.isOnline)) * 31) + Integer.hashCode(this.durationMins)) * 31) + Integer.hashCode(this.totalMaxMarks)) * 31) + this.status.hashCode()) * 31) + this.questions.hashCode()) * 31) + this.date.hashCode()) * 31) + this.time.hashCode()) * 31) + this.location.hashCode()) * 31) + this.syllabus.hashCode()) * 31) + this.instructions.hashCode()) * 31) + Long.hashCode(this.createdAt);
    }

    public String toString() {
        return "FullTestModel(id=" + this.id + ", title=" + this.title + ", batchTitle=" + this.batchTitle + ", isOnline=" + this.isOnline + ", durationMins=" + this.durationMins + ", totalMaxMarks=" + this.totalMaxMarks + ", status=" + this.status + ", questions=" + this.questions + ", date=" + this.date + ", time=" + this.time + ", location=" + this.location + ", syllabus=" + this.syllabus + ", instructions=" + this.instructions + ", createdAt=" + this.createdAt + ")";
    }

    public FullTestModel(String id, String title, String batchTitle, boolean isOnline, int durationMins, int totalMaxMarks, String status, List<ExtendedQuestion> questions, String date, String time, String location, String syllabus, String instructions, long createdAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(questions, "questions");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(syllabus, "syllabus");
        Intrinsics.checkNotNullParameter(instructions, "instructions");
        this.id = id;
        this.title = title;
        this.batchTitle = batchTitle;
        this.isOnline = isOnline;
        this.durationMins = durationMins;
        this.totalMaxMarks = totalMaxMarks;
        this.status = status;
        this.questions = questions;
        this.date = date;
        this.time = time;
        this.location = location;
        this.syllabus = syllabus;
        this.instructions = instructions;
        this.createdAt = createdAt;
    }

    public /* synthetic */ FullTestModel(String str, String str2, String str3, boolean z, int i, int i2, String str4, List list, String str5, String str6, String str7, String str8, String str9, long j, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? "" : str3, (i3 & 8) != 0 ? true : z, (i3 & 16) != 0 ? 30 : i, (i3 & 32) != 0 ? 100 : i2, (i3 & 64) != 0 ? "Active Now" : str4, (i3 & 128) != 0 ? CollectionsKt.emptyList() : list, (i3 & 256) != 0 ? "" : str5, (i3 & 512) != 0 ? "" : str6, (i3 & 1024) != 0 ? "" : str7, (i3 & 2048) != 0 ? "" : str8, (i3 & 4096) == 0 ? str9 : "", (i3 & 8192) != 0 ? System.currentTimeMillis() : j);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getBatchTitle() {
        return this.batchTitle;
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final int getDurationMins() {
        return this.durationMins;
    }

    public final int getTotalMaxMarks() {
        return this.totalMaxMarks;
    }

    public final String getStatus() {
        return this.status;
    }

    public final List<ExtendedQuestion> getQuestions() {
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

    public final long getCreatedAt() {
        return this.createdAt;
    }
}
