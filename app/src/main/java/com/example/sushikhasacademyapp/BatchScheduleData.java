package com.example.sushikhasacademyapp;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/example/sushikhasacademyapp/BatchScheduleData;", "", "days", "", "timeSlot", "venue", "mentor", "topic", "specialNote", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDays", "()Ljava/lang/String;", "getTimeSlot", "getVenue", "getMentor", "getTopic", "getSpecialNote", "component1", "component2", "component3", "component4", "component5", "component6", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class BatchScheduleData {
    public static final int $stable = 0;
    private final String days;
    private final String mentor;
    private final String specialNote;
    private final String timeSlot;
    private final String topic;
    private final String venue;

    public BatchScheduleData() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ BatchScheduleData copy$default(BatchScheduleData batchScheduleData, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = batchScheduleData.days;
        }
        if ((i & 2) != 0) {
            str2 = batchScheduleData.timeSlot;
        }
        if ((i & 4) != 0) {
            str3 = batchScheduleData.venue;
        }
        if ((i & 8) != 0) {
            str4 = batchScheduleData.mentor;
        }
        if ((i & 16) != 0) {
            str5 = batchScheduleData.topic;
        }
        if ((i & 32) != 0) {
            str6 = batchScheduleData.specialNote;
        }
        String str7 = str5;
        String str8 = str6;
        return batchScheduleData.copy(str, str2, str3, str4, str7, str8);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDays() {
        return this.days;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTimeSlot() {
        return this.timeSlot;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVenue() {
        return this.venue;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMentor() {
        return this.mentor;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTopic() {
        return this.topic;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSpecialNote() {
        return this.specialNote;
    }

    public final BatchScheduleData copy(String days, String timeSlot, String venue, String mentor, String topic, String specialNote) {
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(timeSlot, "timeSlot");
        Intrinsics.checkNotNullParameter(venue, "venue");
        Intrinsics.checkNotNullParameter(mentor, "mentor");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(specialNote, "specialNote");
        return new BatchScheduleData(days, timeSlot, venue, mentor, topic, specialNote);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BatchScheduleData)) {
            return false;
        }
        BatchScheduleData batchScheduleData = (BatchScheduleData) other;
        return Intrinsics.areEqual(this.days, batchScheduleData.days) && Intrinsics.areEqual(this.timeSlot, batchScheduleData.timeSlot) && Intrinsics.areEqual(this.venue, batchScheduleData.venue) && Intrinsics.areEqual(this.mentor, batchScheduleData.mentor) && Intrinsics.areEqual(this.topic, batchScheduleData.topic) && Intrinsics.areEqual(this.specialNote, batchScheduleData.specialNote);
    }

    public int hashCode() {
        return (((((((((this.days.hashCode() * 31) + this.timeSlot.hashCode()) * 31) + this.venue.hashCode()) * 31) + this.mentor.hashCode()) * 31) + this.topic.hashCode()) * 31) + this.specialNote.hashCode();
    }

    public String toString() {
        return "BatchScheduleData(days=" + this.days + ", timeSlot=" + this.timeSlot + ", venue=" + this.venue + ", mentor=" + this.mentor + ", topic=" + this.topic + ", specialNote=" + this.specialNote + ")";
    }

    public BatchScheduleData(String days, String timeSlot, String venue, String mentor, String topic, String specialNote) {
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(timeSlot, "timeSlot");
        Intrinsics.checkNotNullParameter(venue, "venue");
        Intrinsics.checkNotNullParameter(mentor, "mentor");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(specialNote, "specialNote");
        this.days = days;
        this.timeSlot = timeSlot;
        this.venue = venue;
        this.mentor = mentor;
        this.topic = topic;
        this.specialNote = specialNote;
    }

    public /* synthetic */ BatchScheduleData(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "Monday & Saturday" : str, (i & 2) != 0 ? "8:30 AM - 10:00 AM" : str2, (i & 4) != 0 ? "Sushiksha Academy • Konnagar Branch • Hall 101" : str3, (i & 8) != 0 ? "Suman Ghosh (M.A. English)" : str4, (i & 16) != 0 ? "Advanced Grammar, Phonetics & Fluency Drills" : str5, (i & 32) != 0 ? "Please arrive 5 minutes prior to class time with notebook and pen." : str6);
    }

    public final String getDays() {
        return this.days;
    }

    public final String getTimeSlot() {
        return this.timeSlot;
    }

    public final String getVenue() {
        return this.venue;
    }

    public final String getMentor() {
        return this.mentor;
    }

    public final String getTopic() {
        return this.topic;
    }

    public final String getSpecialNote() {
        return this.specialNote;
    }
}
