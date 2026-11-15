package androidx.media3.exoplayer.offline;

import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.Comparator;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes11.dex */
@LambdaMethod(holder = "Landroidx/media3/exoplayer/offline/DownloadManager$InternalHandler;", method = "compareStartTimes", proto = "(Landroidx/media3/exoplayer/offline/Download;Landroidx/media3/exoplayer/offline/Download;)I")
@SynthesizedClassV2(apiLevel = -2, kind = 28, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
public final /* synthetic */ class DownloadManager$InternalHandler$$ExternalSyntheticLambda0 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return DownloadManager.InternalHandler.compareStartTimes((Download) obj, (Download) obj2);
    }
}
