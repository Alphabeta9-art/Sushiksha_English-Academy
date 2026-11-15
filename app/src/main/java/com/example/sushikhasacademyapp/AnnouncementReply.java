package com.example.sushikhasacademyapp;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JO\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/example/sushikhasacademyapp/AnnouncementReply;", "", "id", "", "announcementId", "studentUid", "studentName", "studentEmail", "replyText", "timestamp", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getAnnouncementId", "getStudentUid", "getStudentName", "getStudentEmail", "getReplyText", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AnnouncementReply {
    public static final int $stable = 0;
    private final String announcementId;
    private final String id;
    private final String replyText;
    private final String studentEmail;
    private final String studentName;
    private final String studentUid;
    private final String timestamp;

    public AnnouncementReply() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ AnnouncementReply copy$default(AnnouncementReply announcementReply, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = announcementReply.id;
        }
        if ((i & 2) != 0) {
            str2 = announcementReply.announcementId;
        }
        if ((i & 4) != 0) {
            str3 = announcementReply.studentUid;
        }
        if ((i & 8) != 0) {
            str4 = announcementReply.studentName;
        }
        if ((i & 16) != 0) {
            str5 = announcementReply.studentEmail;
        }
        if ((i & 32) != 0) {
            str6 = announcementReply.replyText;
        }
        if ((i & 64) != 0) {
            str7 = announcementReply.timestamp;
        }
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        String str11 = str3;
        return announcementReply.copy(str, str2, str11, str4, str10, str8, str9);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAnnouncementId() {
        return this.announcementId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStudentUid() {
        return this.studentUid;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getStudentName() {
        return this.studentName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStudentEmail() {
        return this.studentEmail;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getReplyText() {
        return this.replyText;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getTimestamp() {
        return this.timestamp;
    }

    public final AnnouncementReply copy(String id, String announcementId, String studentUid, String studentName, String studentEmail, String replyText, String timestamp) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(announcementId, "announcementId");
        Intrinsics.checkNotNullParameter(studentUid, "studentUid");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(studentEmail, "studentEmail");
        Intrinsics.checkNotNullParameter(replyText, "replyText");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        return new AnnouncementReply(id, announcementId, studentUid, studentName, studentEmail, replyText, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnouncementReply)) {
            return false;
        }
        AnnouncementReply announcementReply = (AnnouncementReply) other;
        return Intrinsics.areEqual(this.id, announcementReply.id) && Intrinsics.areEqual(this.announcementId, announcementReply.announcementId) && Intrinsics.areEqual(this.studentUid, announcementReply.studentUid) && Intrinsics.areEqual(this.studentName, announcementReply.studentName) && Intrinsics.areEqual(this.studentEmail, announcementReply.studentEmail) && Intrinsics.areEqual(this.replyText, announcementReply.replyText) && Intrinsics.areEqual(this.timestamp, announcementReply.timestamp);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.announcementId.hashCode()) * 31) + this.studentUid.hashCode()) * 31) + this.studentName.hashCode()) * 31) + this.studentEmail.hashCode()) * 31) + this.replyText.hashCode()) * 31) + this.timestamp.hashCode();
    }

    public String toString() {
        return "AnnouncementReply(id=" + this.id + ", announcementId=" + this.announcementId + ", studentUid=" + this.studentUid + ", studentName=" + this.studentName + ", studentEmail=" + this.studentEmail + ", replyText=" + this.replyText + ", timestamp=" + this.timestamp + ")";
    }

    public AnnouncementReply(String id, String announcementId, String studentUid, String studentName, String studentEmail, String replyText, String timestamp) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(announcementId, "announcementId");
        Intrinsics.checkNotNullParameter(studentUid, "studentUid");
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(studentEmail, "studentEmail");
        Intrinsics.checkNotNullParameter(replyText, "replyText");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        this.id = id;
        this.announcementId = announcementId;
        this.studentUid = studentUid;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.replyText = replyText;
        this.timestamp = timestamp;
    }

    public /* synthetic */ AnnouncementReply(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7);
    }

    public final String getId() {
        return this.id;
    }

    public final String getAnnouncementId() {
        return this.announcementId;
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

    public final String getReplyText() {
        return this.replyText;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }
}
