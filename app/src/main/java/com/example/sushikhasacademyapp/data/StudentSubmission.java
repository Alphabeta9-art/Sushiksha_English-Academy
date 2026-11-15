package com.example.sushikhasacademyapp.data;

import androidx.core.app.NotificationCompat;
import com.cloudinary.metadata.MetadataValidation;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalTestManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b4\b\u0087\b\u0018\u00002\u00020\u0001Bé\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u0015\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\u0015\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u000bHÆ\u0003J\u0015\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bHÆ\u0003J\t\u0010?\u001a\u00020\rHÆ\u0003J\t\u0010@\u001a\u00020\rHÆ\u0003J\t\u0010A\u001a\u00020\rHÆ\u0003J\t\u0010B\u001a\u00020\u0013HÆ\u0003J\t\u0010C\u001a\u00020\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0016HÆ\u0003J\t\u0010E\u001a\u00020\u0016HÆ\u0003J\t\u0010F\u001a\u00020\u0019HÆ\u0003J\t\u0010G\u001a\u00020\u0019HÆ\u0003Jë\u0001\u0010H\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u000b2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0019HÆ\u0001J\u0013\u0010I\u001a\u00020\u00162\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020\rHÖ\u0001J\t\u0010L\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0010\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0011\u0010\u0011\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001eR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u00100R\u0011\u0010\u0017\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b1\u00100R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u001a\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b4\u00103¨\u0006M"}, d2 = {"Lcom/example/sushikhasacademyapp/data/StudentSubmission;", "", "id", "", "testId", "testTitle", "batchTitle", "studentUid", "studentName", "studentEmail", "answersMap", "", "scoresMap", "", "remarksMap", "autoMcqScore", "totalScore", "maxMarks", "percentage", "", NotificationCompat.CATEGORY_STATUS, "isInterrupted", "", "canRetake", "submittedAt", "", "evaluatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;IIIDLjava/lang/String;ZZJJ)V", "getId", "()Ljava/lang/String;", "getTestId", "getTestTitle", "getBatchTitle", "getStudentUid", "getStudentName", "getStudentEmail", "getAnswersMap", "()Ljava/util/Map;", "getScoresMap", "getRemarksMap", "getAutoMcqScore", "()I", "getTotalScore", "getMaxMarks", "getPercentage", "()D", "getStatus", "()Z", "getCanRetake", "getSubmittedAt", "()J", "getEvaluatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", MetadataValidation.EQUALS, "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class StudentSubmission {
    public static final int $stable = 8;
    private final Map<String, String> answersMap;
    private final int autoMcqScore;
    private final String batchTitle;
    private final boolean canRetake;
    private final long evaluatedAt;
    private final String id;
    private final boolean isInterrupted;
    private final int maxMarks;
    private final double percentage;
    private final Map<String, String> remarksMap;
    private final Map<String, Integer> scoresMap;
    private final String status;
    private final String studentEmail;
    private final String studentName;
    private final String studentUid;
    private final long submittedAt;
    private final String testId;
    private final String testTitle;
    private final int totalScore;

    public StudentSubmission() {
        this(null, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0.0d, null, false, false, 0L, 0L, 524287, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StudentSubmission copy$default(StudentSubmission studentSubmission, String str, String str2, String str3, String str4, String str5, String str6, String str7, Map map, Map map2, Map map3, int i, int i2, int i3, double d, String str8, boolean z, boolean z2, long j, long j2, int i4, Object obj) {
        long j3;
        long j4;
        String str9 = (i4 & 1) != 0 ? studentSubmission.id : str;
        String str10 = (i4 & 2) != 0 ? studentSubmission.testId : str2;
        String str11 = (i4 & 4) != 0 ? studentSubmission.testTitle : str3;
        String str12 = (i4 & 8) != 0 ? studentSubmission.batchTitle : str4;
        String str13 = (i4 & 16) != 0 ? studentSubmission.studentUid : str5;
        String str14 = (i4 & 32) != 0 ? studentSubmission.studentName : str6;
        String str15 = (i4 & 64) != 0 ? studentSubmission.studentEmail : str7;
        Map map4 = (i4 & 128) != 0 ? studentSubmission.answersMap : map;
        Map map5 = (i4 & 256) != 0 ? studentSubmission.scoresMap : map2;
        Map map6 = (i4 & 512) != 0 ? studentSubmission.remarksMap : map3;
        int i5 = (i4 & 1024) != 0 ? studentSubmission.autoMcqScore : i;
        int i6 = (i4 & 2048) != 0 ? studentSubmission.totalScore : i2;
        int i7 = (i4 & 4096) != 0 ? studentSubmission.maxMarks : i3;
        String str16 = str9;
        String str17 = str10;
        double d2 = (i4 & 8192) != 0 ? studentSubmission.percentage : d;
        String str18 = (i4 & 16384) != 0 ? studentSubmission.status : str8;
        boolean z3 = (i4 & 32768) != 0 ? studentSubmission.isInterrupted : z;
        boolean z4 = (i4 & 65536) != 0 ? studentSubmission.canRetake : z2;
        double d3 = d2;
        long j5 = (i4 & 131072) != 0 ? studentSubmission.submittedAt : j;
        if ((i4 & 262144) != 0) {
            j4 = j5;
            j3 = studentSubmission.evaluatedAt;
        } else {
            j3 = j2;
            j4 = j5;
        }
        return studentSubmission.copy(str16, str17, str11, str12, str13, str14, str15, map4, map5, map6, i5, i6, i7, d3, str18, z3, z4, j4, j3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final Map<String, String> component10() {
        return this.remarksMap;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getAutoMcqScore() {
        return this.autoMcqScore;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getTotalScore() {
        return this.totalScore;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getMaxMarks() {
        return this.maxMarks;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final double getPercentage() {
        return this.percentage;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getIsInterrupted() {
        return this.isInterrupted;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getCanRetake() {
        return this.canRetake;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final long getSubmittedAt() {
        return this.submittedAt;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final long getEvaluatedAt() {
        return this.evaluatedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTestId() {
        return this.testId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTestTitle() {
        return this.testTitle;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getBatchTitle() {
        return this.batchTitle;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStudentUid() {
        return this.studentUid;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getStudentName() {
        return this.studentName;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getStudentEmail() {
        return this.studentEmail;
    }

    public final Map<String, String> component8() {
        return this.answersMap;
    }

    public final Map<String, Integer> component9() {
        return this.scoresMap;
    }

    public final StudentSubmission copy(String id, String testId, String testTitle, String batchTitle, String studentUid, String studentName, String studentEmail, Map<String, String> answersMap, Map<String, Integer> scoresMap, Map<String, String> remarksMap, int autoMcqScore, int totalScore, int maxMarks, double percentage, String status, boolean isInterrupted, boolean canRetake, long submittedAt, long evaluatedAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(testId, "testId");
        Intrinsics.checkNotNullParameter(testTitle, "testTitle");
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(studentUid, "studentUid");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(studentEmail, "studentEmail");
        Intrinsics.checkNotNullParameter(answersMap, "answersMap");
        Intrinsics.checkNotNullParameter(scoresMap, "scoresMap");
        Intrinsics.checkNotNullParameter(remarksMap, "remarksMap");
        Intrinsics.checkNotNullParameter(status, "status");
        return new StudentSubmission(id, testId, testTitle, batchTitle, studentUid, studentName, studentEmail, answersMap, scoresMap, remarksMap, autoMcqScore, totalScore, maxMarks, percentage, status, isInterrupted, canRetake, submittedAt, evaluatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StudentSubmission)) {
            return false;
        }
        StudentSubmission studentSubmission = (StudentSubmission) other;
        return Intrinsics.areEqual(this.id, studentSubmission.id) && Intrinsics.areEqual(this.testId, studentSubmission.testId) && Intrinsics.areEqual(this.testTitle, studentSubmission.testTitle) && Intrinsics.areEqual(this.batchTitle, studentSubmission.batchTitle) && Intrinsics.areEqual(this.studentUid, studentSubmission.studentUid) && Intrinsics.areEqual(this.studentName, studentSubmission.studentName) && Intrinsics.areEqual(this.studentEmail, studentSubmission.studentEmail) && Intrinsics.areEqual(this.answersMap, studentSubmission.answersMap) && Intrinsics.areEqual(this.scoresMap, studentSubmission.scoresMap) && Intrinsics.areEqual(this.remarksMap, studentSubmission.remarksMap) && this.autoMcqScore == studentSubmission.autoMcqScore && this.totalScore == studentSubmission.totalScore && this.maxMarks == studentSubmission.maxMarks && Double.compare(this.percentage, studentSubmission.percentage) == 0 && Intrinsics.areEqual(this.status, studentSubmission.status) && this.isInterrupted == studentSubmission.isInterrupted && this.canRetake == studentSubmission.canRetake && this.submittedAt == studentSubmission.submittedAt && this.evaluatedAt == studentSubmission.evaluatedAt;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.testId.hashCode()) * 31) + this.testTitle.hashCode()) * 31) + this.batchTitle.hashCode()) * 31) + this.studentUid.hashCode()) * 31) + this.studentName.hashCode()) * 31) + this.studentEmail.hashCode()) * 31) + this.answersMap.hashCode()) * 31) + this.scoresMap.hashCode()) * 31) + this.remarksMap.hashCode()) * 31) + Integer.hashCode(this.autoMcqScore)) * 31) + Integer.hashCode(this.totalScore)) * 31) + Integer.hashCode(this.maxMarks)) * 31) + Double.hashCode(this.percentage)) * 31) + this.status.hashCode()) * 31) + Boolean.hashCode(this.isInterrupted)) * 31) + Boolean.hashCode(this.canRetake)) * 31) + Long.hashCode(this.submittedAt)) * 31) + Long.hashCode(this.evaluatedAt);
    }

    public String toString() {
        return "StudentSubmission(id=" + this.id + ", testId=" + this.testId + ", testTitle=" + this.testTitle + ", batchTitle=" + this.batchTitle + ", studentUid=" + this.studentUid + ", studentName=" + this.studentName + ", studentEmail=" + this.studentEmail + ", answersMap=" + this.answersMap + ", scoresMap=" + this.scoresMap + ", remarksMap=" + this.remarksMap + ", autoMcqScore=" + this.autoMcqScore + ", totalScore=" + this.totalScore + ", maxMarks=" + this.maxMarks + ", percentage=" + this.percentage + ", status=" + this.status + ", isInterrupted=" + this.isInterrupted + ", canRetake=" + this.canRetake + ", submittedAt=" + this.submittedAt + ", evaluatedAt=" + this.evaluatedAt + ")";
    }

    public StudentSubmission(String id, String testId, String testTitle, String batchTitle, String studentUid, String studentName, String studentEmail, Map<String, String> answersMap, Map<String, Integer> scoresMap, Map<String, String> remarksMap, int autoMcqScore, int totalScore, int maxMarks, double percentage, String status, boolean isInterrupted, boolean canRetake, long submittedAt, long evaluatedAt) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(testId, "testId");
        Intrinsics.checkNotNullParameter(testTitle, "testTitle");
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        Intrinsics.checkNotNullParameter(studentUid, "studentUid");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(studentEmail, "studentEmail");
        Intrinsics.checkNotNullParameter(answersMap, "answersMap");
        Intrinsics.checkNotNullParameter(scoresMap, "scoresMap");
        Intrinsics.checkNotNullParameter(remarksMap, "remarksMap");
        Intrinsics.checkNotNullParameter(status, "status");
        this.id = id;
        this.testId = testId;
        this.testTitle = testTitle;
        this.batchTitle = batchTitle;
        this.studentUid = studentUid;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.answersMap = answersMap;
        this.scoresMap = scoresMap;
        this.remarksMap = remarksMap;
        this.autoMcqScore = autoMcqScore;
        this.totalScore = totalScore;
        this.maxMarks = maxMarks;
        this.percentage = percentage;
        this.status = status;
        this.isInterrupted = isInterrupted;
        this.canRetake = canRetake;
        this.submittedAt = submittedAt;
        this.evaluatedAt = evaluatedAt;
    }

    public /* synthetic */ StudentSubmission(String str, String str2, String str3, String str4, String str5, String str6, String str7, Map map, Map map2, Map map3, int i, int i2, int i3, double d, String str8, boolean z, boolean z2, long j, long j2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? "" : str2, (i4 & 4) != 0 ? "" : str3, (i4 & 8) != 0 ? "" : str4, (i4 & 16) != 0 ? "" : str5, (i4 & 32) != 0 ? "" : str6, (i4 & 64) == 0 ? str7 : "", (i4 & 128) != 0 ? MapsKt.emptyMap() : map, (i4 & 256) != 0 ? MapsKt.emptyMap() : map2, (i4 & 512) != 0 ? MapsKt.emptyMap() : map3, (i4 & 1024) != 0 ? 0 : i, (i4 & 2048) != 0 ? 0 : i2, (i4 & 4096) != 0 ? 0 : i3, (i4 & 8192) != 0 ? 0.0d : d, (i4 & 16384) != 0 ? "Pending Evaluation" : str8, (i4 & 32768) != 0 ? false : z, (i4 & 65536) != 0 ? false : z2, (i4 & 131072) != 0 ? System.currentTimeMillis() : j, (i4 & 262144) != 0 ? 0L : j2);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTestId() {
        return this.testId;
    }

    public final String getTestTitle() {
        return this.testTitle;
    }

    public final String getBatchTitle() {
        return this.batchTitle;
    }

    public final String getStudentUid() {
        return this.studentUid;
    }

    public final String getStudentName() {
        return this.studentName;
    }

    public final String getStudentEmail() {
        return this.studentEmail;
    }

    public final Map<String, String> getAnswersMap() {
        return this.answersMap;
    }

    public final Map<String, Integer> getScoresMap() {
        return this.scoresMap;
    }

    public final Map<String, String> getRemarksMap() {
        return this.remarksMap;
    }

    public final int getAutoMcqScore() {
        return this.autoMcqScore;
    }

    public final int getTotalScore() {
        return this.totalScore;
    }

    public final int getMaxMarks() {
        return this.maxMarks;
    }

    public final double getPercentage() {
        return this.percentage;
    }

    public final String getStatus() {
        return this.status;
    }

    public final boolean isInterrupted() {
        return this.isInterrupted;
    }

    public final boolean getCanRetake() {
        return this.canRetake;
    }

    public final long getSubmittedAt() {
        return this.submittedAt;
    }

    public final long getEvaluatedAt() {
        return this.evaluatedAt;
    }
}
