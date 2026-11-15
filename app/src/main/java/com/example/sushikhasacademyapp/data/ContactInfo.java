package com.example.sushikhasacademyapp.data;

import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AcademyModels.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/example/sushikhasacademyapp/data/ContactInfo;", "", "address", "", "phone", "email", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getPhone", "setPhone", "getEmail", "setEmail", "component1", "component2", "component3", "copy", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ContactInfo {
    public static final int $stable = 8;
    private String address;
    private String email;
    private String phone;

    public ContactInfo() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ ContactInfo copy$default(ContactInfo contactInfo, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contactInfo.address;
        }
        if ((i & 2) != 0) {
            str2 = contactInfo.phone;
        }
        if ((i & 4) != 0) {
            str3 = contactInfo.email;
        }
        return contactInfo.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final ContactInfo copy(String address, String phone, String email) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(email, "email");
        return new ContactInfo(address, phone, email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContactInfo)) {
            return false;
        }
        ContactInfo contactInfo = (ContactInfo) other;
        return Intrinsics.areEqual(this.address, contactInfo.address) && Intrinsics.areEqual(this.phone, contactInfo.phone) && Intrinsics.areEqual(this.email, contactInfo.email);
    }

    public int hashCode() {
        return (((this.address.hashCode() * 31) + this.phone.hashCode()) * 31) + this.email.hashCode();
    }

    public String toString() {
        return "ContactInfo(address=" + this.address + ", phone=" + this.phone + ", email=" + this.email + ")";
    }

    public ContactInfo(String address, String phone, String email) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(email, "email");
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public /* synthetic */ ContactInfo(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "3, Vivekananda Road, Nabagram, Konnagar, Hooghly - 712246 (Near Holy Mission School)" : str, (i & 2) != 0 ? "+91 9836926092" : str2, (i & 4) != 0 ? "sushiksha.konnagar@gmail.com" : str3);
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.address = str;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phone = str;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.email = str;
    }
}
