package androidx.media3.exoplayer;

import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.media3.common.util.Log;

/* JADX INFO: loaded from: classes11.dex */
final class WifiLockManager {
    private static final String TAG = "WifiLockManager";
    private static final String WIFI_LOCK_TAG = "ExoPlayer:WifiLockManager";
    private boolean enabled;
    private boolean stayAwake;
    private WifiManager.WifiLock wifiLock;
    private final WifiManager wifiManager;

    public WifiLockManager(Context context) {
        this.wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    public void setEnabled(boolean enabled) {
        if (enabled && this.wifiLock == null) {
            if (this.wifiManager == null) {
                Log.w(TAG, "WifiManager is null, therefore not creating the WifiLock.");
                return;
            } else {
                this.wifiLock = this.wifiManager.createWifiLock(3, WIFI_LOCK_TAG);
                this.wifiLock.setReferenceCounted(false);
            }
        }
        this.enabled = enabled;
        updateWifiLock();
    }

    public void setStayAwake(boolean stayAwake) {
        this.stayAwake = stayAwake;
        updateWifiLock();
    }

    private void updateWifiLock() {
        if (this.wifiLock == null) {
            return;
        }
        if (this.enabled && this.stayAwake) {
            this.wifiLock.acquire();
        } else {
            this.wifiLock.release();
        }
    }
}
