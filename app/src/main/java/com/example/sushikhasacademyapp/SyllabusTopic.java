package com.example.sushikhasacademyapp;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/example/sushikhasacademyapp/SyllabusTopic;", "", "id", "", "title", "isCompleted", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getTitle", "()Z", "component1", "component2", "component3", "copy", MetadataValidation.EQUALS, "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SyllabusTopic {
    public static final int $stable = 0;
    private final String id;
    private final boolean isCompleted;
    private final String title;

    public static /* synthetic */ SyllabusTopic copy$default(SyllabusTopic syllabusTopic, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = syllabusTopic.id;
        }
        if ((i & 2) != 0) {
            str2 = syllabusTopic.title;
        }
        if ((i & 4) != 0) {
            z = syllabusTopic.isCompleted;
        }
        return syllabusTopic.copy(str, str2, z);
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
    public final boolean getIsCompleted() {
        return this.isCompleted;
    }

    public final SyllabusTopic copy(String id, String title, boolean isCompleted) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        return new SyllabusTopic(id, title, isCompleted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyllabusTopic)) {
            return false;
        }
        SyllabusTopic syllabusTopic = (SyllabusTopic) other;
        return Intrinsics.areEqual(this.id, syllabusTopic.id) && Intrinsics.areEqual(this.title, syllabusTopic.title) && this.isCompleted == syllabusTopic.isCompleted;
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + Boolean.hashCode(this.isCompleted);
    }

    public String toString() {
        return "SyllabusTopic(id=" + this.id + ", title=" + this.title + ", isCompleted=" + this.isCompleted + ")";
    }

    public SyllabusTopic(String id, String title, boolean isCompleted) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public /* synthetic */ SyllabusTopic(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, str2, (i & 4) != 0 ? false : z);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final boolean isCompleted() {
        return this.isCompleted;
    }
}
