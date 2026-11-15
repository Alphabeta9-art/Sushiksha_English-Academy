package com.example.sushikhasacademyapp.data;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AcademyModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003Jc\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012¨\u00065"}, d2 = {"Lcom/example/sushikhasacademyapp/data/CourseInfo;", "", "id", "", "title", "description", TypedValues.TransitionType.S_DURATION, "mode", "level", "fee", "rating", "", "iconName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getTitle", "setTitle", "getDescription", "setDescription", "getDuration", "setDuration", "getMode", "setMode", "getLevel", "setLevel", "getFee", "setFee", "getRating", "()D", "setRating", "(D)V", "getIconName", "setIconName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class CourseInfo {
    public static final int $stable = 8;
    private String description;
    private String duration;
    private String fee;
    private String iconName;
    private String id;
    private String level;
    private String mode;
    private double rating;
    private String title;

    public CourseInfo() {
        this(null, null, null, null, null, null, null, 0.0d, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public static /* synthetic */ CourseInfo copy$default(CourseInfo courseInfo, String str, String str2, String str3, String str4, String str5, String str6, String str7, double d, String str8, int i, Object obj) {
        if ((i & 1) != 0) {
            str = courseInfo.id;
        }
        if ((i & 2) != 0) {
            str2 = courseInfo.title;
        }
        if ((i & 4) != 0) {
            str3 = courseInfo.description;
        }
        if ((i & 8) != 0) {
            str4 = courseInfo.duration;
        }
        if ((i & 16) != 0) {
            str5 = courseInfo.mode;
        }
        if ((i & 32) != 0) {
            str6 = courseInfo.level;
        }
        if ((i & 64) != 0) {
            str7 = courseInfo.fee;
        }
        if ((i & 128) != 0) {
            d = courseInfo.rating;
        }
        if ((i & 256) != 0) {
            str8 = courseInfo.iconName;
        }
        String str9 = str8;
        double d2 = d;
        String str10 = str6;
        String str11 = str7;
        String str12 = str5;
        String str13 = str3;
        return courseInfo.copy(str, str2, str13, str4, str12, str10, str11, d2, str9);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMode() {
        return this.mode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLevel() {
        return this.level;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getFee() {
        return this.fee;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final double getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getIconName() {
        return this.iconName;
    }

    public final CourseInfo copy(String id, String title, String description, String duration, String mode, String level, String fee, double rating, String iconName) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(duration, "duration");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(fee, "fee");
        Intrinsics.checkNotNullParameter(iconName, "iconName");
        return new CourseInfo(id, title, description, duration, mode, level, fee, rating, iconName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CourseInfo)) {
            return false;
        }
        CourseInfo courseInfo = (CourseInfo) other;
        return Intrinsics.areEqual(this.id, courseInfo.id) && Intrinsics.areEqual(this.title, courseInfo.title) && Intrinsics.areEqual(this.description, courseInfo.description) && Intrinsics.areEqual(this.duration, courseInfo.duration) && Intrinsics.areEqual(this.mode, courseInfo.mode) && Intrinsics.areEqual(this.level, courseInfo.level) && Intrinsics.areEqual(this.fee, courseInfo.fee) && Double.compare(this.rating, courseInfo.rating) == 0 && Intrinsics.areEqual(this.iconName, courseInfo.iconName);
    }

    public int hashCode() {
        return (((((((((((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31) + this.duration.hashCode()) * 31) + this.mode.hashCode()) * 31) + this.level.hashCode()) * 31) + this.fee.hashCode()) * 31) + Double.hashCode(this.rating)) * 31) + this.iconName.hashCode();
    }

    public String toString() {
        return "CourseInfo(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", duration=" + this.duration + ", mode=" + this.mode + ", level=" + this.level + ", fee=" + this.fee + ", rating=" + this.rating + ", iconName=" + this.iconName + ")";
    }

    public CourseInfo(String id, String title, String description, String duration, String mode, String level, String fee, double rating, String iconName) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(duration, "duration");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(fee, "fee");
        Intrinsics.checkNotNullParameter(iconName, "iconName");
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.mode = mode;
        this.level = level;
        this.fee = fee;
        this.rating = rating;
        this.iconName = iconName;
    }

    public /* synthetic */ CourseInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, double d, String str8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7, (i & 128) != 0 ? 0.0d : d, (i & 256) != 0 ? "" : str8);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final void setDuration(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.duration = str;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mode = str;
    }

    public final String getLevel() {
        return this.level;
    }

    public final void setLevel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.level = str;
    }

    public final String getFee() {
        return this.fee;
    }

    public final void setFee(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fee = str;
    }

    public final double getRating() {
        return this.rating;
    }

    public final void setRating(double d) {
        this.rating = d;
    }

    public final String getIconName() {
        return this.iconName;
    }

    public final void setIconName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.iconName = str;
    }
}
