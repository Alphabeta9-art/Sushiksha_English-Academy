package androidx.media3.exoplayer.source;

import android.os.Bundle;
import androidx.media3.common.Bundleable;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.BundleableUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class TrackGroupArray implements Bundleable {
    private static final String TAG = "TrackGroupArray";
    private int hashCode;
    public final int length;
    private final ImmutableList<TrackGroup> trackGroups;
    public static final TrackGroupArray EMPTY = new TrackGroupArray(new TrackGroup[0]);
    private static final String FIELD_TRACK_GROUPS = Util.intToStringMaxRadix(0);
    public static final Bundleable.Creator<TrackGroupArray> CREATOR = new Bundleable.Creator() { // from class: androidx.media3.exoplayer.source.TrackGroupArray$$ExternalSyntheticLambda0
        @Override // androidx.media3.common.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            return TrackGroupArray.lambda$static$0(bundle);
        }
    };

    public TrackGroupArray(TrackGroup... trackGroups) {
        this.trackGroups = ImmutableList.copyOf(trackGroups);
        this.length = trackGroups.length;
        verifyCorrectness();
    }

    public TrackGroup get(int index) {
        return this.trackGroups.get(index);
    }

    public int indexOf(TrackGroup group) {
        int index = this.trackGroups.indexOf(group);
        if (index >= 0) {
            return index;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.trackGroups.hashCode();
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackGroupArray other = (TrackGroupArray) obj;
        if (this.length == other.length && this.trackGroups.equals(other.trackGroups)) {
            return true;
        }
        return false;
    }

    @Override // androidx.media3.common.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FIELD_TRACK_GROUPS, BundleableUtil.toBundleArrayList(this.trackGroups));
        return bundle;
    }

    static /* synthetic */ TrackGroupArray lambda$static$0(Bundle bundle) {
        List<Bundle> trackGroupBundles = bundle.getParcelableArrayList(FIELD_TRACK_GROUPS);
        if (trackGroupBundles == null) {
            return new TrackGroupArray(new TrackGroup[0]);
        }
        return new TrackGroupArray((TrackGroup[]) BundleableUtil.fromBundleList(TrackGroup.CREATOR, trackGroupBundles).toArray(new TrackGroup[0]));
    }

    private void verifyCorrectness() {
        for (int i = 0; i < this.trackGroups.size(); i++) {
            for (int j = i + 1; j < this.trackGroups.size(); j++) {
                if (this.trackGroups.get(i).equals(this.trackGroups.get(j))) {
                    Log.e(TAG, "", new IllegalArgumentException("Multiple identical TrackGroups added to one TrackGroupArray."));
                }
            }
        }
    }
}
