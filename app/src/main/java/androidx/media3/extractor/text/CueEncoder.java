package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.BundleableUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class CueEncoder {
    public byte[] encode(List<Cue> cues) {
        ArrayList<Bundle> bundledCues = BundleableUtil.toBundleArrayList(cues);
        Bundle allCuesBundle = new Bundle();
        allCuesBundle.putParcelableArrayList("c", bundledCues);
        Parcel parcel = Parcel.obtain();
        parcel.writeBundle(allCuesBundle);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }
}
