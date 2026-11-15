package com.example.sushikhasacademyapp.data;

import androidx.autofill.HintConstants;
import com.cloudinary.metadata.MetadataValidation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AcademyModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\tHÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\rHÆ\u0003J_\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rHÆ\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u00020\tHÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0011\"\u0004\b!\u0010\u0013R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0011\"\u0004\b#\u0010\u0013R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u00066"}, d2 = {"Lcom/example/sushikhasacademyapp/data/TeacherProfile;", "", HintConstants.AUTOFILL_HINT_NAME, "", "title", "credentials", "rating", "", "studentsGuided", "", "dpUrl", "bio", "specialties", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DILjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getTitle", "setTitle", "getCredentials", "setCredentials", "getRating", "()D", "setRating", "(D)V", "getStudentsGuided", "()I", "setStudentsGuided", "(I)V", "getDpUrl", "setDpUrl", "getBio", "setBio", "getSpecialties", "()Ljava/util/List;", "setSpecialties", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TeacherProfile {
    public static final int $stable = 8;
    private String bio;
    private String credentials;
    private String dpUrl;
    private String name;
    private double rating;
    private List<String> specialties;
    private int studentsGuided;
    private String title;

    public TeacherProfile() {
        this(null, null, null, 0.0d, 0, null, null, null, 255, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TeacherProfile copy$default(TeacherProfile teacherProfile, String str, String str2, String str3, double d, int i, String str4, String str5, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = teacherProfile.name;
        }
        if ((i2 & 2) != 0) {
            str2 = teacherProfile.title;
        }
        if ((i2 & 4) != 0) {
            str3 = teacherProfile.credentials;
        }
        if ((i2 & 8) != 0) {
            d = teacherProfile.rating;
        }
        if ((i2 & 16) != 0) {
            i = teacherProfile.studentsGuided;
        }
        if ((i2 & 32) != 0) {
            str4 = teacherProfile.dpUrl;
        }
        if ((i2 & 64) != 0) {
            str5 = teacherProfile.bio;
        }
        if ((i2 & 128) != 0) {
            list = teacherProfile.specialties;
        }
        double d2 = d;
        String str6 = str3;
        return teacherProfile.copy(str, str2, str6, d2, i, str4, str5, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCredentials() {
        return this.credentials;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final double getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getStudentsGuided() {
        return this.studentsGuided;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getDpUrl() {
        return this.dpUrl;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getBio() {
        return this.bio;
    }

    public final List<String> component8() {
        return this.specialties;
    }

    public final TeacherProfile copy(String name, String title, String credentials, double rating, int studentsGuided, String dpUrl, String bio, List<String> specialties) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        Intrinsics.checkNotNullParameter(dpUrl, "dpUrl");
        Intrinsics.checkNotNullParameter(bio, "bio");
        Intrinsics.checkNotNullParameter(specialties, "specialties");
        return new TeacherProfile(name, title, credentials, rating, studentsGuided, dpUrl, bio, specialties);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TeacherProfile)) {
            return false;
        }
        TeacherProfile teacherProfile = (TeacherProfile) other;
        return Intrinsics.areEqual(this.name, teacherProfile.name) && Intrinsics.areEqual(this.title, teacherProfile.title) && Intrinsics.areEqual(this.credentials, teacherProfile.credentials) && Double.compare(this.rating, teacherProfile.rating) == 0 && this.studentsGuided == teacherProfile.studentsGuided && Intrinsics.areEqual(this.dpUrl, teacherProfile.dpUrl) && Intrinsics.areEqual(this.bio, teacherProfile.bio) && Intrinsics.areEqual(this.specialties, teacherProfile.specialties);
    }

    public int hashCode() {
        return (((((((((((((this.name.hashCode() * 31) + this.title.hashCode()) * 31) + this.credentials.hashCode()) * 31) + Double.hashCode(this.rating)) * 31) + Integer.hashCode(this.studentsGuided)) * 31) + this.dpUrl.hashCode()) * 31) + this.bio.hashCode()) * 31) + this.specialties.hashCode();
    }

    public String toString() {
        return "TeacherProfile(name=" + this.name + ", title=" + this.title + ", credentials=" + this.credentials + ", rating=" + this.rating + ", studentsGuided=" + this.studentsGuided + ", dpUrl=" + this.dpUrl + ", bio=" + this.bio + ", specialties=" + this.specialties + ")";
    }

    public TeacherProfile(String name, String title, String credentials, double rating, int studentsGuided, String dpUrl, String bio, List<String> specialties) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        Intrinsics.checkNotNullParameter(dpUrl, "dpUrl");
        Intrinsics.checkNotNullParameter(bio, "bio");
        Intrinsics.checkNotNullParameter(specialties, "specialties");
        this.name = name;
        this.title = title;
        this.credentials = credentials;
        this.rating = rating;
        this.studentsGuided = studentsGuided;
        this.dpUrl = dpUrl;
        this.bio = bio;
        this.specialties = specialties;
    }

    public /* synthetic */ TeacherProfile(String str, String str2, String str3, double d, int i, String str4, String str5, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? 0.0d : d, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? "" : str4, (i2 & 64) != 0 ? "" : str5, (i2 & 128) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final String getCredentials() {
        return this.credentials;
    }

    public final void setCredentials(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.credentials = str;
    }

    public final double getRating() {
        return this.rating;
    }

    public final void setRating(double d) {
        this.rating = d;
    }

    public final int getStudentsGuided() {
        return this.studentsGuided;
    }

    public final void setStudentsGuided(int i) {
        this.studentsGuided = i;
    }

    public final String getDpUrl() {
        return this.dpUrl;
    }

    public final void setDpUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dpUrl = str;
    }

    public final String getBio() {
        return this.bio;
    }

    public final void setBio(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bio = str;
    }

    public final List<String> getSpecialties() {
        return this.specialties;
    }

    public final void setSpecialties(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.specialties = list;
    }
}
