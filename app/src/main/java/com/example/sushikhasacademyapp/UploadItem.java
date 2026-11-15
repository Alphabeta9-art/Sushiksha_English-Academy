package com.example.sushikhasacademyapp;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/example/sushikhasacademyapp/UploadItem;", "", "id", "", "title", "meta", "isImage", "", "imageUri", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getMeta", "()Z", "getImageUri", "component1", "component2", "component3", "component4", "component5", "copy", MetadataValidation.EQUALS, "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class UploadItem {
    public static final int $stable = 0;
    private final String id;
    private final String imageUri;
    private final boolean isImage;
    private final String meta;
    private final String title;

    public static /* synthetic */ UploadItem copy$default(UploadItem uploadItem, String str, String str2, String str3, boolean z, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uploadItem.id;
        }
        if ((i & 2) != 0) {
            str2 = uploadItem.title;
        }
        if ((i & 4) != 0) {
            str3 = uploadItem.meta;
        }
        if ((i & 8) != 0) {
            z = uploadItem.isImage;
        }
        if ((i & 16) != 0) {
            str4 = uploadItem.imageUri;
        }
        String str5 = str4;
        String str6 = str3;
        return uploadItem.copy(str, str2, str6, z, str5);
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
    public final String getMeta() {
        return this.meta;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsImage() {
        return this.isImage;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getImageUri() {
        return this.imageUri;
    }

    public final UploadItem copy(String id, String title, String meta, boolean isImage, String imageUri) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(meta, "meta");
        return new UploadItem(id, title, meta, isImage, imageUri);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UploadItem)) {
            return false;
        }
        UploadItem uploadItem = (UploadItem) other;
        return Intrinsics.areEqual(this.id, uploadItem.id) && Intrinsics.areEqual(this.title, uploadItem.title) && Intrinsics.areEqual(this.meta, uploadItem.meta) && this.isImage == uploadItem.isImage && Intrinsics.areEqual(this.imageUri, uploadItem.imageUri);
    }

    public int hashCode() {
        return (((((((this.id.hashCode() * 31) + this.title.hashCode()) * 31) + this.meta.hashCode()) * 31) + Boolean.hashCode(this.isImage)) * 31) + (this.imageUri == null ? 0 : this.imageUri.hashCode());
    }

    public String toString() {
        return "UploadItem(id=" + this.id + ", title=" + this.title + ", meta=" + this.meta + ", isImage=" + this.isImage + ", imageUri=" + this.imageUri + ")";
    }

    public UploadItem(String id, String title, String meta, boolean isImage, String imageUri) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(meta, "meta");
        this.id = id;
        this.title = title;
        this.meta = meta;
        this.isImage = isImage;
        this.imageUri = imageUri;
    }

    public /* synthetic */ UploadItem(String str, String str2, String str3, boolean z, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, str2, str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? null : str4);
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getMeta() {
        return this.meta;
    }

    public final boolean isImage() {
        return this.isImage;
    }

    public final String getImageUri() {
        return this.imageUri;
    }
}
