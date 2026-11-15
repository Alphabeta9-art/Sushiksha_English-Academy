package com.example.sushikhasacademyapp.data;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AcademyModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b#\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003JO\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010)\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\bHÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011¨\u0006-"}, d2 = {"Lcom/example/sushikhasacademyapp/data/BatchSchedule;", "", "id", "", "courseTitle", "timing", "days", "seatsLeft", "", "isOnline", "", "mode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getCourseTitle", "setCourseTitle", "getTiming", "setTiming", "getDays", "setDays", "getSeatsLeft", "()I", "setSeatsLeft", "(I)V", "()Z", "setOnline", "(Z)V", "getMode", "setMode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", MetadataValidation.EQUALS, "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class BatchSchedule {
    public static final int $stable = 8;
    private String courseTitle;
    private String days;
    private String id;
    private boolean isOnline;
    private String mode;
    private int seatsLeft;
    private String timing;

    public BatchSchedule() {
        this(null, null, null, null, 0, false, null, 127, null);
    }

    public static /* synthetic */ BatchSchedule copy$default(BatchSchedule batchSchedule, String str, String str2, String str3, String str4, int i, boolean z, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = batchSchedule.id;
        }
        if ((i2 & 2) != 0) {
            str2 = batchSchedule.courseTitle;
        }
        if ((i2 & 4) != 0) {
            str3 = batchSchedule.timing;
        }
        if ((i2 & 8) != 0) {
            str4 = batchSchedule.days;
        }
        if ((i2 & 16) != 0) {
            i = batchSchedule.seatsLeft;
        }
        if ((i2 & 32) != 0) {
            z = batchSchedule.isOnline;
        }
        if ((i2 & 64) != 0) {
            str5 = batchSchedule.mode;
        }
        boolean z2 = z;
        String str6 = str5;
        int i3 = i;
        String str7 = str3;
        return batchSchedule.copy(str, str2, str7, str4, i3, z2, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCourseTitle() {
        return this.courseTitle;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTiming() {
        return this.timing;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDays() {
        return this.days;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getSeatsLeft() {
        return this.seatsLeft;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsOnline() {
        return this.isOnline;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getMode() {
        return this.mode;
    }

    public final BatchSchedule copy(String id, String courseTitle, String timing, String days, int seatsLeft, boolean isOnline, String mode) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(courseTitle, "courseTitle");
        Intrinsics.checkNotNullParameter(timing, "timing");
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(mode, "mode");
        return new BatchSchedule(id, courseTitle, timing, days, seatsLeft, isOnline, mode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BatchSchedule)) {
            return false;
        }
        BatchSchedule batchSchedule = (BatchSchedule) other;
        return Intrinsics.areEqual(this.id, batchSchedule.id) && Intrinsics.areEqual(this.courseTitle, batchSchedule.courseTitle) && Intrinsics.areEqual(this.timing, batchSchedule.timing) && Intrinsics.areEqual(this.days, batchSchedule.days) && this.seatsLeft == batchSchedule.seatsLeft && this.isOnline == batchSchedule.isOnline && Intrinsics.areEqual(this.mode, batchSchedule.mode);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.courseTitle.hashCode()) * 31) + this.timing.hashCode()) * 31) + this.days.hashCode()) * 31) + Integer.hashCode(this.seatsLeft)) * 31) + Boolean.hashCode(this.isOnline)) * 31) + this.mode.hashCode();
    }

    public String toString() {
        return "BatchSchedule(id=" + this.id + ", courseTitle=" + this.courseTitle + ", timing=" + this.timing + ", days=" + this.days + ", seatsLeft=" + this.seatsLeft + ", isOnline=" + this.isOnline + ", mode=" + this.mode + ")";
    }

    public BatchSchedule(String id, String courseTitle, String timing, String days, int seatsLeft, boolean isOnline, String mode) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(courseTitle, "courseTitle");
        Intrinsics.checkNotNullParameter(timing, "timing");
        Intrinsics.checkNotNullParameter(days, "days");
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.id = id;
        this.courseTitle = courseTitle;
        this.timing = timing;
        this.days = days;
        this.seatsLeft = seatsLeft;
        this.isOnline = isOnline;
        this.mode = mode;
    }

    public /* synthetic */ BatchSchedule(String str, String str2, String str3, String str4, int i, boolean z, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? "Offline Classroom" : str5);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getCourseTitle() {
        return this.courseTitle;
    }

    public final void setCourseTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.courseTitle = str;
    }

    public final String getTiming() {
        return this.timing;
    }

    public final void setTiming(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.timing = str;
    }

    public final String getDays() {
        return this.days;
    }

    public final void setDays(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.days = str;
    }

    public final int getSeatsLeft() {
        return this.seatsLeft;
    }

    public final void setSeatsLeft(int i) {
        this.seatsLeft = i;
    }

    public final boolean isOnline() {
        return this.isOnline;
    }

    public final void setOnline(boolean z) {
        this.isOnline = z;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mode = str;
    }
}
