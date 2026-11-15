package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleableUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes11.dex */
public final class CueDecoder {
    static final String BUNDLED_CUES = "c";

    public ImmutableList<Cue> decode(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        Bundle bundle = parcel.readBundle(Bundle.class.getClassLoader());
        parcel.recycle();
        ArrayList<Bundle> bundledCues = (ArrayList) Assertions.checkNotNull(bundle.getParcelableArrayList(BUNDLED_CUES));
        return BundleableUtil.fromBundleList(Cue.CREATOR, bundledCues);
    }
}
