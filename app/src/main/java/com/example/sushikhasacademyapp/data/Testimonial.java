package com.example.sushikhasacademyapp.data;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AcademyModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0007HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f¨\u0006#"}, d2 = {"Lcom/example/sushikhasacademyapp/data/Testimonial;", "", "studentName", "", "courseTaken", "review", "rating", "", "source", "isTopGoogleReview", "", "location", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;)V", "getStudentName", "()Ljava/lang/String;", "getCourseTaken", "getReview", "getRating", "()I", "getSource", "()Z", "getLocation", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", MetadataValidation.EQUALS, "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Testimonial {
    public static final int $stable = 0;
    private final String courseTaken;
    private final boolean isTopGoogleReview;
    private final String location;
    private final int rating;
    private final String review;
    private final String source;
    private final String studentName;

    public static /* synthetic */ Testimonial copy$default(Testimonial testimonial, String str, String str2, String str3, int i, String str4, boolean z, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = testimonial.studentName;
        }
        if ((i2 & 2) != 0) {
            str2 = testimonial.courseTaken;
        }
        if ((i2 & 4) != 0) {
            str3 = testimonial.review;
        }
        if ((i2 & 8) != 0) {
            i = testimonial.rating;
        }
        if ((i2 & 16) != 0) {
            str4 = testimonial.source;
        }
        if ((i2 & 32) != 0) {
            z = testimonial.isTopGoogleReview;
        }
        if ((i2 & 64) != 0) {
            str5 = testimonial.location;
        }
        boolean z2 = z;
        String str6 = str5;
        String str7 = str4;
        String str8 = str3;
        return testimonial.copy(str, str2, str8, i, str7, z2, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getStudentName() {
        return this.studentName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCourseTaken() {
        return this.courseTaken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getReview() {
        return this.review;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsTopGoogleReview() {
        return this.isTopGoogleReview;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    public final Testimonial copy(String studentName, String courseTaken, String review, int rating, String source, boolean isTopGoogleReview, String location) {
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(courseTaken, "courseTaken");
        Intrinsics.checkNotNullParameter(review, "review");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(location, "location");
        return new Testimonial(studentName, courseTaken, review, rating, source, isTopGoogleReview, location);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Testimonial)) {
            return false;
        }
        Testimonial testimonial = (Testimonial) other;
        return Intrinsics.areEqual(this.studentName, testimonial.studentName) && Intrinsics.areEqual(this.courseTaken, testimonial.courseTaken) && Intrinsics.areEqual(this.review, testimonial.review) && this.rating == testimonial.rating && Intrinsics.areEqual(this.source, testimonial.source) && this.isTopGoogleReview == testimonial.isTopGoogleReview && Intrinsics.areEqual(this.location, testimonial.location);
    }

    public int hashCode() {
        return (((((((((((this.studentName.hashCode() * 31) + this.courseTaken.hashCode()) * 31) + this.review.hashCode()) * 31) + Integer.hashCode(this.rating)) * 31) + this.source.hashCode()) * 31) + Boolean.hashCode(this.isTopGoogleReview)) * 31) + this.location.hashCode();
    }

    public String toString() {
        return "Testimonial(studentName=" + this.studentName + ", courseTaken=" + this.courseTaken + ", review=" + this.review + ", rating=" + this.rating + ", source=" + this.source + ", isTopGoogleReview=" + this.isTopGoogleReview + ", location=" + this.location + ")";
    }

    public Testimonial(String studentName, String courseTaken, String review, int rating, String source, boolean isTopGoogleReview, String location) {
        Intrinsics.checkNotNullParameter(studentName, "studentName");
        Intrinsics.checkNotNullParameter(courseTaken, "courseTaken");
        Intrinsics.checkNotNullParameter(review, "review");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(location, "location");
        this.studentName = studentName;
        this.courseTaken = courseTaken;
        this.review = review;
        this.rating = rating;
        this.source = source;
        this.isTopGoogleReview = isTopGoogleReview;
        this.location = location;
    }

    public /* synthetic */ Testimonial(String str, String str2, String str3, int i, String str4, boolean z, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, (i2 & 16) != 0 ? "Student" : str4, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? "" : str5);
    }

    public final String getStudentName() {
        return this.studentName;
    }

    public final String getCourseTaken() {
        return this.courseTaken;
    }

    public final String getReview() {
        return this.review;
    }

    public final int getRating() {
        return this.rating;
    }

    public final String getSource() {
        return this.source;
    }

    public final boolean isTopGoogleReview() {
        return this.isTopGoogleReview;
    }

    public final String getLocation() {
        return this.location;
    }
}
