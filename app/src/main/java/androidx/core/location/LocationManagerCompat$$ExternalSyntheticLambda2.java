package androidx.core.location;

import android.location.LocationManager;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes10.dex */
@LambdaMethod(holder = "Landroidx/core/location/LocationManagerCompat;", method = "lambda$registerGnssStatusCallback$1", proto = "(Landroid/location/LocationManager;Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;)Ljava/lang/Boolean;")
@SynthesizedClassV2(apiLevel = -2, kind = 28, versionHash = "f3a348d7d571cfc3f89005b557595f12bc85b7451dde524d9d228b1d59adcb6b")
public final /* synthetic */ class LocationManagerCompat$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ LocationManager f$0;
    public final /* synthetic */ LocationManagerCompat.GpsStatusTransport f$1;

    public /* synthetic */ LocationManagerCompat$$ExternalSyntheticLambda2(LocationManager locationManager, LocationManagerCompat.GpsStatusTransport gpsStatusTransport) {
        this.f$0 = locationManager;
        this.f$1 = gpsStatusTransport;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return Boolean.valueOf(this.f$0.addGpsStatusListener(this.f$1));
    }
}
