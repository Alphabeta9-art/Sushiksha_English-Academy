package com.google.android.recaptcha.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbk {
    public zzbk() {
        new ConcurrentHashMap();
        zzc();
    }

    public static final Set zza(Context context) {
        try {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = Build.VERSION.SDK_INT;
            Object systemService = context.getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(1)) {
                linkedHashSet.add(zznz.TRANSPORT_WIFI);
            }
            if (networkCapabilities != null && networkCapabilities.hasTransport(0)) {
                linkedHashSet.add(zznz.TRANSPORT_CELLULAR);
            }
            if (networkCapabilities != null && networkCapabilities.hasTransport(4)) {
                linkedHashSet.add(zznz.TRANSPORT_VPN);
            }
            if (networkCapabilities != null && networkCapabilities.hasTransport(3)) {
                linkedHashSet.add(zznz.TRANSPORT_ETHERNET);
            }
            if (networkCapabilities != null && networkCapabilities.hasCapability(16)) {
                linkedHashSet.add(zznz.NET_CAPABILITY_VALIDATED);
            }
            return linkedHashSet;
        } catch (Exception e) {
            return SetsKt.emptySet();
        }
    }

    public static final boolean zzb(Context context) {
        NetworkCapabilities networkCapabilities;
        try {
            Object systemService = context.getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            int i = Build.VERSION.SDK_INT;
            Network activeNetwork = connectivityManager.getActiveNetwork();
            return (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(16)) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    private static final Map zzc() {
        Map mapMutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(0, zznz.NET_CAPABILITY_MMS), TuplesKt.to(1, zznz.NET_CAPABILITY_SUPL), TuplesKt.to(2, zznz.NET_CAPABILITY_DUN), TuplesKt.to(3, zznz.NET_CAPABILITY_FOTA), TuplesKt.to(4, zznz.NET_CAPABILITY_IMS), TuplesKt.to(5, zznz.NET_CAPABILITY_CBS), TuplesKt.to(6, zznz.NET_CAPABILITY_WIFI_P2P), TuplesKt.to(7, zznz.NET_CAPABILITY_IA), TuplesKt.to(8, zznz.NET_CAPABILITY_RCS), TuplesKt.to(9, zznz.NET_CAPABILITY_XCAP), TuplesKt.to(10, zznz.NET_CAPABILITY_EIMS), TuplesKt.to(11, zznz.NET_CAPABILITY_NOT_METERED), TuplesKt.to(12, zznz.NET_CAPABILITY_INTERNET), TuplesKt.to(13, zznz.NET_CAPABILITY_NOT_RESTRICTED), TuplesKt.to(14, zznz.NET_CAPABILITY_TRUSTED), TuplesKt.to(15, zznz.NET_CAPABILITY_NOT_VPN));
        int i = Build.VERSION.SDK_INT;
        mapMutableMapOf.put(17, zznz.NET_CAPABILITY_CAPTIVE_PORTAL);
        mapMutableMapOf.put(16, zznz.NET_CAPABILITY_VALIDATED);
        if (Build.VERSION.SDK_INT >= 28) {
            mapMutableMapOf.put(18, zznz.NET_CAPABILITY_NOT_ROAMING);
            mapMutableMapOf.put(19, zznz.NET_CAPABILITY_FOREGROUND);
            mapMutableMapOf.put(20, zznz.NET_CAPABILITY_NOT_CONGESTED);
            mapMutableMapOf.put(21, zznz.NET_CAPABILITY_NOT_SUSPENDED);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            mapMutableMapOf.put(23, zznz.NET_CAPABILITY_MCX);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            mapMutableMapOf.put(25, zznz.NET_CAPABILITY_TEMPORARILY_NOT_METERED);
        }
        if (Build.VERSION.SDK_INT >= 31) {
            mapMutableMapOf.put(32, zznz.NET_CAPABILITY_HEAD_UNIT);
            mapMutableMapOf.put(29, zznz.NET_CAPABILITY_ENTERPRISE);
        }
        if (Build.VERSION.SDK_INT >= 33) {
            mapMutableMapOf.put(35, zznz.NET_CAPABILITY_PRIORITIZE_BANDWIDTH);
            mapMutableMapOf.put(34, zznz.NET_CAPABILITY_PRIORITIZE_LATENCY);
            mapMutableMapOf.put(33, zznz.NET_CAPABILITY_MMTEL);
        }
        return mapMutableMapOf;
    }
}
