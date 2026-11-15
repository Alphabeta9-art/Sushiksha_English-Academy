package androidx.media3.container;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.Metadata;
import com.google.common.primitives.Longs;

/* JADX INFO: loaded from: classes11.dex */
public final class CreationTime implements Metadata.Entry {
    public static final Parcelable.Creator<CreationTime> CREATOR = new Parcelable.Creator<CreationTime>() { // from class: androidx.media3.container.CreationTime.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreationTime createFromParcel(Parcel in) {
            return new CreationTime(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreationTime[] newArray(int size) {
            return new CreationTime[size];
        }
    };
    public final long timestampMs;

    public CreationTime(long timestampMs) {
        this.timestampMs = timestampMs;
    }

    private CreationTime(Parcel in) {
        this.timestampMs = in.readLong();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CreationTime) && this.timestampMs == ((CreationTime) obj).timestampMs;
    }

    public int hashCode() {
        return Longs.hashCode(this.timestampMs);
    }

    public String toString() {
        return "Creation time: " + (this.timestampMs == -2082844800000L ? "unset" : Long.valueOf(this.timestampMs));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.timestampMs);
    }
}
