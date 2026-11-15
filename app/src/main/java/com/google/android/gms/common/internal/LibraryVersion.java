package com.google.android.gms.common.internal;

import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
/* JADX INFO: loaded from: classes11.dex */
@Deprecated
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static final LibraryVersion zzb = new LibraryVersion();
    private final ConcurrentHashMap zzc = new ConcurrentHashMap();

    protected LibraryVersion() {
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00a7  */
    @Deprecated
    public String getVersion(String str) throws Throwable {
        String str2;
        InputStream resourceAsStream;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzc.containsKey(str)) {
            return (String) this.zzc.get(str);
        }
        Properties properties = new Properties();
        InputStream inputStream = null;
        property = null;
        String property = null;
        inputStream = null;
        try {
            resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", str));
            try {
                if (resourceAsStream != null) {
                    properties.load(resourceAsStream);
                    property = properties.getProperty("version", null);
                    zza.v("LibraryVersion", str + " version is " + property);
                } else {
                    zza.w("LibraryVersion", "Failed to get app version for libraryName: " + str);
                }
            } catch (IOException e) {
                e = e;
                str2 = property;
                inputStream = resourceAsStream;
                try {
                    zza.e("LibraryVersion", "Failed to get app version for libraryName: " + str, e);
                    String str3 = str2;
                    resourceAsStream = inputStream;
                    property = str3;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        IOUtils.closeQuietly(inputStream);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = resourceAsStream;
                if (inputStream != null) {
                    IOUtils.closeQuietly(inputStream);
                }
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            str2 = null;
        } catch (Throwable th3) {
            th = th3;
        }
        if (resourceAsStream != null) {
            IOUtils.closeQuietly(resourceAsStream);
        }
        if (property == null) {
            zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
            property = "UNKNOWN";
        }
        this.zzc.put(str, property);
        return property;
    }
}
