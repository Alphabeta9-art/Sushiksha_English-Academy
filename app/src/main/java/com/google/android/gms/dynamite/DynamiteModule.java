package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.media3.extractor.ts.TsExtractor;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static Boolean zzb;
    private static String zzc;
    private static boolean zzd;
    private static zzq zzk;
    private static zzr zzl;
    private final Context zzj;
    private static int zze = -1;
    private static Boolean zzf = null;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy zza = new zzl();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    public static int getLocalVersion(Context context, String moduleId) {
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + moduleId + ".ModuleDescriptor");
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), moduleId)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + moduleId + "'");
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", "Local module descriptor class for " + moduleId + " not found.");
            return 0;
        } catch (Exception e2) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e2.getMessage())));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String moduleId) {
        return zza(context, moduleId, false);
    }

    /* JADX WARN: Code duplicated, block: B:113:0x023f  */
    /* JADX WARN: Code duplicated, block: B:114:0x0245  */
    /* JADX WARN: Code duplicated, block: B:117:0x0252  */
    /* JADX WARN: Code duplicated, block: B:122:0x0263 A[Catch: all -> 0x02aa, TryCatch #5 {all -> 0x02aa, blocks: (B:7:0x004c, B:11:0x007a, B:16:0x0082, B:19:0x0088, B:22:0x0090, B:98:0x01f3, B:99:0x01fd, B:101:0x01ff, B:103:0x0201, B:104:0x0208, B:122:0x0263, B:123:0x0279, B:106:0x020a, B:108:0x0228, B:110:0x0237, B:120:0x025b, B:121:0x0262, B:124:0x027a, B:125:0x02a9), top: B:144:0x004c, inners: #7 }] */
    /* JADX WARN: Code duplicated, block: B:141:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:143:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:147:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:19:0x0088 A[Catch: all -> 0x02aa, TRY_LEAVE, TryCatch #5 {all -> 0x02aa, blocks: (B:7:0x004c, B:11:0x007a, B:16:0x0082, B:19:0x0088, B:22:0x0090, B:98:0x01f3, B:99:0x01fd, B:101:0x01ff, B:103:0x0201, B:104:0x0208, B:122:0x0263, B:123:0x0279, B:106:0x020a, B:108:0x0228, B:110:0x0237, B:120:0x025b, B:121:0x0262, B:124:0x027a, B:125:0x02a9), top: B:144:0x004c, inners: #7 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x008e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x009b A[Catch: all -> 0x01f0, TryCatch #1 {, blocks: (B:25:0x0095, B:27:0x009b, B:28:0x009d, B:91:0x01e6, B:92:0x01ed), top: B:143:0x0095 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x00a0 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TRY_ENTER, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a7 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00cd A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TRY_ENTER, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x0146 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0152 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0176 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x017d A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0187 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x0196 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x019e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x01a0 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x01b0 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x01c5 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x01ce A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01d6 A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01de A[Catch: all -> 0x01f2, LoadingException -> 0x01fe, RemoteException -> 0x0200, TryCatch #8 {RemoteException -> 0x0200, LoadingException -> 0x01fe, all -> 0x01f2, blocks: (B:24:0x0094, B:30:0x00a0, B:32:0x00a7, B:33:0x00c7, B:37:0x00cd, B:39:0x00d7, B:41:0x00db, B:42:0x00e6, B:49:0x00f5, B:51:0x00fb, B:53:0x0122, B:55:0x012a, B:56:0x0132, B:57:0x0139, B:52:0x010f, B:59:0x013b, B:62:0x013e, B:63:0x0145, B:64:0x0146, B:65:0x014d, B:67:0x014f, B:70:0x0152, B:72:0x0176, B:74:0x017d, B:76:0x0187, B:82:0x01bf, B:84:0x01c5, B:85:0x01ce, B:86:0x01d5, B:77:0x0196, B:78:0x019d, B:80:0x01a0, B:81:0x01b0, B:87:0x01d6, B:88:0x01dd, B:89:0x01de, B:90:0x01e5, B:94:0x01ef), top: B:150:0x0094 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x01e6 A[Catch: all -> 0x01f0, TRY_ENTER, TryCatch #1 {, blocks: (B:25:0x0095, B:27:0x009b, B:28:0x009d, B:91:0x01e6, B:92:0x01ed), top: B:143:0x0095 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:122:0x0263, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:32:0x00a7, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:70:0x0152, please report this as an issue */
    @ResultIgnorabilityUnspecified
    public static DynamiteModule load(Context context, VersionPolicy policy, String moduleId) throws Throwable {
        long j;
        DynamiteModule dynamiteModuleZzc;
        int i;
        Boolean bool;
        zzq zzqVarZzg;
        int iZze;
        IObjectWrapper iObjectWrapperZzh;
        Object objUnwrap;
        zzn zznVar;
        zzr zzrVar;
        zzn zznVar2;
        Boolean boolValueOf;
        IObjectWrapper iObjectWrapperZze;
        Cursor cursor;
        Context applicationContext = context.getApplicationContext();
        zzp zzpVar = null;
        if (applicationContext == null) {
            throw new LoadingException("null application Context", zzpVar);
        }
        zzn zznVar3 = (zzn) zzg.get();
        zzn zznVar4 = new zzn(null);
        zzg.set(zznVar4);
        long jLongValue = ((Long) zzh.get()).longValue();
        try {
            zzh.set(Long.valueOf(SystemClock.elapsedRealtime()));
            VersionPolicy.SelectionResult selectionResultSelectModule = policy.selectModule(context, moduleId, zzi);
            int i2 = selectionResultSelectModule.localVersion;
            int i3 = selectionResultSelectModule.remoteVersion;
            StringBuilder sb = new StringBuilder();
            j = 0;
            try {
                sb.append("Considering local module ");
                sb.append(moduleId);
                sb.append(":");
                sb.append(i2);
                sb.append(" and remote module ");
                sb.append(moduleId);
                sb.append(":");
                sb.append(i3);
                Log.i("DynamiteModule", sb.toString());
                int i4 = selectionResultSelectModule.selection;
                if (i4 != 0) {
                    if (i4 != -1) {
                        if (i4 == 1 || selectionResultSelectModule.remoteVersion != 0) {
                            if (i4 == -1) {
                                dynamiteModuleZzc = zzc(applicationContext, moduleId);
                            } else {
                                if (i4 == 1) {
                                    throw new LoadingException("VersionPolicy returned invalid code:" + i4, zzpVar);
                                }
                                try {
                                    i = selectionResultSelectModule.remoteVersion;
                                    try {
                                        synchronized (DynamiteModule.class) {
                                            if (zzf(context)) {
                                                throw new LoadingException("Remote loading disabled", zzpVar);
                                            }
                                            bool = zzb;
                                        }
                                        if (bool != null) {
                                            throw new LoadingException("Failed to determine which loading route to use.", zzpVar);
                                        }
                                        if (bool.booleanValue()) {
                                            Log.i("DynamiteModule", "Selected remote version of " + moduleId + ", version >= " + i);
                                            synchronized (DynamiteModule.class) {
                                                try {
                                                    zzrVar = zzl;
                                                } catch (Throwable th) {
                                                    throw th;
                                                }
                                            }
                                            if (zzrVar != null) {
                                                throw new LoadingException("DynamiteLoaderV2 was not cached.", zzpVar);
                                            }
                                            zznVar2 = (zzn) zzg.get();
                                            if (zznVar2 != null || zznVar2.zza == null) {
                                                throw new LoadingException("No result cursor", zzpVar);
                                            }
                                            Context applicationContext2 = context.getApplicationContext();
                                            Cursor cursor2 = zznVar2.zza;
                                            ObjectWrapper.wrap(null);
                                            synchronized (DynamiteModule.class) {
                                                try {
                                                    boolValueOf = Boolean.valueOf(zze >= 2);
                                                } catch (Throwable th2) {
                                                    throw th2;
                                                }
                                            }
                                            if (boolValueOf.booleanValue()) {
                                                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                                iObjectWrapperZze = zzrVar.zzf(ObjectWrapper.wrap(applicationContext2), moduleId, i, ObjectWrapper.wrap(cursor2));
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                                iObjectWrapperZze = zzrVar.zze(ObjectWrapper.wrap(applicationContext2), moduleId, i, ObjectWrapper.wrap(cursor2));
                                            }
                                            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapperZze);
                                            if (context2 == null) {
                                                throw new LoadingException("Failed to get module context", zzpVar);
                                            }
                                            dynamiteModuleZzc = new DynamiteModule(context2);
                                        } else {
                                            Log.i("DynamiteModule", "Selected remote version of " + moduleId + ", version >= " + i);
                                            zzqVarZzg = zzg(context);
                                            if (zzqVarZzg != null) {
                                                throw new LoadingException("Failed to create IDynamiteLoader.", zzpVar);
                                            }
                                            iZze = zzqVarZzg.zze();
                                            if (iZze >= 3) {
                                                zznVar = (zzn) zzg.get();
                                                if (zznVar != null) {
                                                    throw new LoadingException("No cached result cursor holder", zzpVar);
                                                }
                                                iObjectWrapperZzh = zzqVarZzg.zzi(ObjectWrapper.wrap(context), moduleId, i, ObjectWrapper.wrap(zznVar.zza));
                                            } else if (iZze == 2) {
                                                Log.w("DynamiteModule", "IDynamite loader version = 2");
                                                iObjectWrapperZzh = zzqVarZzg.zzj(ObjectWrapper.wrap(context), moduleId, i);
                                            } else {
                                                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                                iObjectWrapperZzh = zzqVarZzg.zzh(ObjectWrapper.wrap(context), moduleId, i);
                                            }
                                            objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZzh);
                                            if (objUnwrap != null) {
                                                throw new LoadingException("Failed to load remote module.", zzpVar);
                                            }
                                            dynamiteModuleZzc = new DynamiteModule((Context) objUnwrap);
                                        }
                                    } catch (RemoteException e) {
                                        throw new LoadingException("Failed to load remote module.", e, zzpVar);
                                    } catch (LoadingException e2) {
                                        throw e2;
                                    } catch (Throwable th3) {
                                        CrashUtils.addDynamiteErrorToDropBox(context, th3);
                                        throw new LoadingException("Failed to load remote module.", th3, zzpVar);
                                    }
                                } catch (LoadingException e3) {
                                    Log.w("DynamiteModule", "Failed to load remote module: " + e3.getMessage());
                                    int i5 = selectionResultSelectModule.localVersion;
                                    if (i5 == 0 || policy.selectModule(context, moduleId, new zzo(i5, 0)).selection != -1) {
                                        throw new LoadingException("Remote load failed. No local fallback found.", e3, zzpVar);
                                    }
                                    dynamiteModuleZzc = zzc(applicationContext, moduleId);
                                }
                            }
                            if (jLongValue == 0) {
                                zzh.remove();
                            } else {
                                zzh.set(Long.valueOf(jLongValue));
                            }
                            cursor = zznVar4.zza;
                            if (cursor != null) {
                                cursor.close();
                            }
                            zzg.set(zznVar3);
                            return dynamiteModuleZzc;
                        }
                    } else if (selectionResultSelectModule.localVersion != 0) {
                        i4 = -1;
                        if (i4 == 1) {
                        }
                        if (i4 == -1) {
                            dynamiteModuleZzc = zzc(applicationContext, moduleId);
                        } else {
                            if (i4 == 1) {
                                throw new LoadingException("VersionPolicy returned invalid code:" + i4, zzpVar);
                            }
                            i = selectionResultSelectModule.remoteVersion;
                            synchronized (DynamiteModule.class) {
                                if (zzf(context)) {
                                    throw new LoadingException("Remote loading disabled", zzpVar);
                                }
                                bool = zzb;
                                if (bool != null) {
                                    throw new LoadingException("Failed to determine which loading route to use.", zzpVar);
                                }
                                if (bool.booleanValue()) {
                                    Log.i("DynamiteModule", "Selected remote version of " + moduleId + ", version >= " + i);
                                    synchronized (DynamiteModule.class) {
                                        zzrVar = zzl;
                                        if (zzrVar != null) {
                                            throw new LoadingException("DynamiteLoaderV2 was not cached.", zzpVar);
                                        }
                                        zznVar2 = (zzn) zzg.get();
                                        if (zznVar2 != null) {
                                        }
                                        throw new LoadingException("No result cursor", zzpVar);
                                    }
                                }
                                Log.i("DynamiteModule", "Selected remote version of " + moduleId + ", version >= " + i);
                                zzqVarZzg = zzg(context);
                                if (zzqVarZzg != null) {
                                    throw new LoadingException("Failed to create IDynamiteLoader.", zzpVar);
                                }
                                iZze = zzqVarZzg.zze();
                                if (iZze >= 3) {
                                    zznVar = (zzn) zzg.get();
                                    if (zznVar != null) {
                                        throw new LoadingException("No cached result cursor holder", zzpVar);
                                    }
                                    iObjectWrapperZzh = zzqVarZzg.zzi(ObjectWrapper.wrap(context), moduleId, i, ObjectWrapper.wrap(zznVar.zza));
                                } else if (iZze == 2) {
                                    Log.w("DynamiteModule", "IDynamite loader version = 2");
                                    iObjectWrapperZzh = zzqVarZzg.zzj(ObjectWrapper.wrap(context), moduleId, i);
                                } else {
                                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                    iObjectWrapperZzh = zzqVarZzg.zzh(ObjectWrapper.wrap(context), moduleId, i);
                                }
                                objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZzh);
                                if (objUnwrap != null) {
                                    throw new LoadingException("Failed to load remote module.", zzpVar);
                                }
                                dynamiteModuleZzc = new DynamiteModule((Context) objUnwrap);
                            }
                        }
                        if (jLongValue == 0) {
                            zzh.remove();
                        } else {
                            zzh.set(Long.valueOf(jLongValue));
                        }
                        cursor = zznVar4.zza;
                        if (cursor != null) {
                            cursor.close();
                        }
                        zzg.set(zznVar3);
                        return dynamiteModuleZzc;
                    }
                }
                throw new LoadingException("No acceptable module " + moduleId + " found. Local version is " + selectionResultSelectModule.localVersion + " and remote version is " + selectionResultSelectModule.remoteVersion + ".", zzpVar);
            } catch (Throwable th4) {
                th = th4;
                if (jLongValue == j) {
                    zzh.remove();
                } else {
                    zzh.set(Long.valueOf(jLongValue));
                }
                Cursor cursor3 = zznVar4.zza;
                if (cursor3 != null) {
                    cursor3.close();
                }
                zzg.set(zznVar3);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            j = 0;
        }
    }

    /* JADX WARN: Code duplicated, block: B:123:0x01d2 A[Catch: all -> 0x01db, TryCatch #12 {all -> 0x01db, blocks: (B:3:0x0002, B:65:0x00e2, B:67:0x00e8, B:73:0x010a, B:95:0x0163, B:99:0x0173, B:123:0x01d2, B:124:0x01d5, B:116:0x01c7, B:71:0x00ef, B:126:0x01d7, B:4:0x0003, B:7:0x0009, B:8:0x0026, B:63:0x00df, B:21:0x0049, B:43:0x009e, B:46:0x00a1, B:54:0x00bb, B:64:0x00e1, B:62:0x00c3), top: B:141:0x0002, inners: #6, #9 }] */
    /* JADX WARN: Code duplicated, block: B:149:? A[Catch: all -> 0x01db, SYNTHETIC, TRY_LEAVE, TryCatch #12 {all -> 0x01db, blocks: (B:3:0x0002, B:65:0x00e2, B:67:0x00e8, B:73:0x010a, B:95:0x0163, B:99:0x0173, B:123:0x01d2, B:124:0x01d5, B:116:0x01c7, B:71:0x00ef, B:126:0x01d7, B:4:0x0003, B:7:0x0009, B:8:0x0026, B:63:0x00df, B:21:0x0049, B:43:0x009e, B:46:0x00a1, B:54:0x00bb, B:64:0x00e1, B:62:0x00c3), top: B:141:0x0002, inners: #6, #9 }] */
    public static int zza(Context context, String str, boolean z) {
        Throwable th;
        RemoteException remoteException;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zzb;
                Cursor cursor2 = null;
                int iZzf = 0;
                if (bool == null) {
                    try {
                        Field declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                        synchronized (declaredField.getDeclaringClass()) {
                            try {
                                ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else if (classLoader != null) {
                                    try {
                                        zzd(classLoader);
                                    } catch (LoadingException e) {
                                    }
                                    bool = Boolean.TRUE;
                                } else {
                                    if (!zzf(context)) {
                                        return 0;
                                    }
                                    if (zzd || Boolean.TRUE.equals(null)) {
                                        declaredField.set(null, ClassLoader.getSystemClassLoader());
                                        bool = Boolean.FALSE;
                                    } else {
                                        try {
                                            int iZzb = zzb(context, str, z, true);
                                            String str2 = zzc;
                                            if (str2 != null && !str2.isEmpty()) {
                                                ClassLoader classLoaderZza = zzb.zza();
                                                if (classLoaderZza == null) {
                                                    if (Build.VERSION.SDK_INT >= 29) {
                                                        String str3 = zzc;
                                                        Preconditions.checkNotNull(str3);
                                                        classLoaderZza = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                                    } else {
                                                        String str4 = zzc;
                                                        Preconditions.checkNotNull(str4);
                                                        classLoaderZza = new zzc(str4, ClassLoader.getSystemClassLoader());
                                                    }
                                                }
                                                zzd(classLoaderZza);
                                                declaredField.set(null, classLoaderZza);
                                                zzb = Boolean.TRUE;
                                                return iZzb;
                                            }
                                            return iZzb;
                                        } catch (LoadingException e2) {
                                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                                            bool = Boolean.FALSE;
                                        }
                                    }
                                }
                                zzb = bool;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e3) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + e3.toString());
                        bool = Boolean.FALSE;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzb(context, str, z, false);
                    } catch (LoadingException e4) {
                        Log.w("DynamiteModule", "Failed to retrieve remote module version: " + e4.getMessage());
                        return 0;
                    }
                }
                zzq zzqVarZzg = zzg(context);
                if (zzqVarZzg != null) {
                    try {
                        int iZze = zzqVarZzg.zze();
                        if (iZze >= 3) {
                            zzn zznVar = (zzn) zzg.get();
                            if (zznVar == null || (cursor = zznVar.zza) == null) {
                                Cursor cursor3 = (Cursor) ObjectWrapper.unwrap(zzqVarZzg.zzk(ObjectWrapper.wrap(context), str, z, ((Long) zzh.get()).longValue()));
                                if (cursor3 != null) {
                                    try {
                                        if (cursor3.moveToFirst()) {
                                            int i = cursor3.getInt(0);
                                            cursor2 = (i <= 0 || !zze(cursor3)) ? cursor3 : null;
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                            iZzf = i;
                                        }
                                    } catch (RemoteException e5) {
                                        remoteException = e5;
                                        cursor2 = cursor3;
                                        try {
                                            Log.w("DynamiteModule", "Failed to retrieve remote module version: " + remoteException.getMessage());
                                            if (cursor2 != null) {
                                                cursor2.close();
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            if (cursor2 != null) {
                                                throw th;
                                            }
                                            cursor2.close();
                                            throw th;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        cursor2 = cursor3;
                                        if (cursor2 != null) {
                                            throw th;
                                        }
                                        cursor2.close();
                                        throw th;
                                    }
                                }
                                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                if (cursor3 != null) {
                                    cursor3.close();
                                }
                            } else {
                                iZzf = cursor.getInt(0);
                            }
                        } else if (iZze == 2) {
                            Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                            iZzf = zzqVarZzg.zzg(ObjectWrapper.wrap(context), str, z);
                        } else {
                            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                            iZzf = zzqVarZzg.zzf(ObjectWrapper.wrap(context), str, z);
                        }
                    } catch (RemoteException e6) {
                        remoteException = e6;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                return iZzf;
            }
        } catch (Throwable th6) {
            CrashUtils.addDynamiteErrorToDropBox(context, th6);
            throw th6;
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00d0 A[Catch: all -> 0x00ec, TryCatch #5 {all -> 0x00ec, blocks: (B:56:0x00cc, B:58:0x00d0, B:59:0x00d1, B:60:0x00eb), top: B:68:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:59:0x00d1 A[Catch: all -> 0x00ec, TryCatch #5 {all -> 0x00ec, blocks: (B:56:0x00cc, B:58:0x00d0, B:59:0x00d1, B:60:0x00eb), top: B:68:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:64:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:74:? A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:59:0x00d1, please report this as an issue */
    private static int zzb(Context context, String str, boolean z, boolean z2) throws Throwable {
        Throwable th;
        Exception exc;
        Cursor cursorQuery;
        Cursor cursor = null;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        try {
            boolean z3 = true;
            cursorQuery = context.getContentResolver().query(new Uri.Builder().scheme(UriUtil.LOCAL_CONTENT_SCHEME).authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartTime", String.valueOf(((Long) zzh.get()).longValue())).build(), null, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        boolean z4 = false;
                        int i = cursorQuery.getInt(0);
                        if (i > 0) {
                            synchronized (DynamiteModule.class) {
                                zzc = cursorQuery.getString(2);
                                int columnIndex = cursorQuery.getColumnIndex("loaderVersion");
                                if (columnIndex >= 0) {
                                    zze = cursorQuery.getInt(columnIndex);
                                }
                                int columnIndex2 = cursorQuery.getColumnIndex("disableStandaloneDynamiteLoader2");
                                if (columnIndex2 >= 0) {
                                    if (cursorQuery.getInt(columnIndex2) == 0) {
                                        z3 = false;
                                    }
                                    zzd = z3;
                                    z4 = z3;
                                }
                            }
                            if (zze(cursorQuery)) {
                                cursorQuery = null;
                            }
                        }
                        if (!z2 || !z4) {
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return i;
                        }
                        try {
                            throw new LoadingException("forcing fallback to container DynamiteLoader impl", b2 == true ? 1 : 0);
                        } catch (Exception e) {
                            exc = e;
                            try {
                                if (exc instanceof LoadingException) {
                                    throw exc;
                                }
                                throw new LoadingException("V2 version check failed: " + exc.getMessage(), exc, b == true ? 1 : 0);
                            } catch (Throwable th2) {
                                th = th2;
                                cursor = cursorQuery;
                                if (cursor == null) {
                                    throw th;
                                }
                                cursor.close();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            cursor = cursorQuery;
                            if (cursor == null) {
                                throw th;
                            }
                            cursor.close();
                            throw th;
                        }
                    }
                } catch (Exception e2) {
                    exc = e2;
                    if (exc instanceof LoadingException) {
                        throw exc;
                    }
                    throw new LoadingException("V2 version check failed: " + exc.getMessage(), exc, b == true ? 1 : 0);
                } catch (Throwable th4) {
                    th = th4;
                    cursor = cursorQuery;
                    if (cursor == null) {
                        throw th;
                    }
                    cursor.close();
                    throw th;
                }
            }
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", b3 == true ? 1 : 0);
        } catch (Exception e3) {
            exc = e3;
            cursorQuery = null;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = iInterfaceQueryLocalInterface instanceof zzr ? (zzr) iInterfaceQueryLocalInterface : new zzr(iBinder);
            }
            zzl = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzpVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zznVar = (zzn) zzg.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    private static boolean zzf(Context context) {
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(zzf)) {
            return true;
        }
        boolean zBooleanValue = false;
        if (zzf == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                zBooleanValue = true;
            }
            Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
            zzf = boolValueOf;
            zBooleanValue = boolValueOf.booleanValue();
            if (zBooleanValue && providerInfoResolveContentProvider.applicationInfo != null && (providerInfoResolveContentProvider.applicationInfo.flags & TsExtractor.TS_STREAM_TYPE_AC3) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!zBooleanValue) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return zBooleanValue;
    }

    private static zzq zzg(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = zzk;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    zzk = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    @ResultIgnorabilityUnspecified
    public Context getModuleContext() {
        return this.zzj;
    }

    public IBinder instantiate(String className) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(className).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(className)), e, null);
        }
    }
}
