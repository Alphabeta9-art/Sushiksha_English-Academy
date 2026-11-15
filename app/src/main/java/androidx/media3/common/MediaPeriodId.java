package androidx.media3.common;

/* JADX INFO: loaded from: classes11.dex */
public class MediaPeriodId {
    public final int adGroupIndex;
    public final int adIndexInAdGroup;
    public final int nextAdGroupIndex;
    public final Object periodUid;
    public final long windowSequenceNumber;

    public MediaPeriodId(Object periodUid) {
        this(periodUid, -1L);
    }

    public MediaPeriodId(Object periodUid, long windowSequenceNumber) {
        this(periodUid, -1, -1, windowSequenceNumber, -1);
    }

    public MediaPeriodId(Object periodUid, long windowSequenceNumber, int nextAdGroupIndex) {
        this(periodUid, -1, -1, windowSequenceNumber, nextAdGroupIndex);
    }

    public MediaPeriodId(Object periodUid, int adGroupIndex, int adIndexInAdGroup, long windowSequenceNumber) {
        this(periodUid, adGroupIndex, adIndexInAdGroup, windowSequenceNumber, -1);
    }

    protected MediaPeriodId(MediaPeriodId mediaPeriodId) {
        this.periodUid = mediaPeriodId.periodUid;
        this.adGroupIndex = mediaPeriodId.adGroupIndex;
        this.adIndexInAdGroup = mediaPeriodId.adIndexInAdGroup;
        this.windowSequenceNumber = mediaPeriodId.windowSequenceNumber;
        this.nextAdGroupIndex = mediaPeriodId.nextAdGroupIndex;
    }

    private MediaPeriodId(Object periodUid, int adGroupIndex, int adIndexInAdGroup, long windowSequenceNumber, int nextAdGroupIndex) {
        this.periodUid = periodUid;
        this.adGroupIndex = adGroupIndex;
        this.adIndexInAdGroup = adIndexInAdGroup;
        this.windowSequenceNumber = windowSequenceNumber;
        this.nextAdGroupIndex = nextAdGroupIndex;
    }

    public MediaPeriodId copyWithPeriodUid(Object newPeriodUid) {
        if (this.periodUid.equals(newPeriodUid)) {
            return this;
        }
        return new MediaPeriodId(newPeriodUid, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
    }

    public MediaPeriodId copyWithWindowSequenceNumber(long windowSequenceNumber) {
        if (this.windowSequenceNumber == windowSequenceNumber) {
            return this;
        }
        return new MediaPeriodId(this.periodUid, this.adGroupIndex, this.adIndexInAdGroup, windowSequenceNumber, this.nextAdGroupIndex);
    }

    public boolean isAd() {
        return this.adGroupIndex != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPeriodId)) {
            return false;
        }
        MediaPeriodId periodId = (MediaPeriodId) obj;
        return this.periodUid.equals(periodId.periodUid) && this.adGroupIndex == periodId.adGroupIndex && this.adIndexInAdGroup == periodId.adIndexInAdGroup && this.windowSequenceNumber == periodId.windowSequenceNumber && this.nextAdGroupIndex == periodId.nextAdGroupIndex;
    }

    public int hashCode() {
        int result = (17 * 31) + this.periodUid.hashCode();
        return (((((((result * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
    }
}
