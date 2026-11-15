package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ApplicationSoSource extends SoSource {
    private Context applicationContext;
    private int flags;
    private DirectorySoSource soSource;

    public ApplicationSoSource(Context context, int flags) {
        this.applicationContext = context.getApplicationContext();
        if (this.applicationContext == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.ApplicationSoSource fallbacks to: " + context.getApplicationInfo().nativeLibraryDir);
            this.applicationContext = context;
        }
        this.flags = flags;
        this.soSource = new DirectorySoSource(new File(this.applicationContext.getApplicationInfo().nativeLibraryDir), flags);
    }

    public boolean checkAndMaybeUpdate() throws IOException {
        File nativeLibDir = this.soSource.soDirectory;
        Context updatedContext = getUpdatedContext();
        File updatedNativeLibDir = getNativeLibDirFromContext(updatedContext);
        if (!nativeLibDir.equals(updatedNativeLibDir)) {
            Log.d("SoLoader", "Native library directory updated from " + nativeLibDir + " to " + updatedNativeLibDir);
            this.flags |= 1;
            this.soSource = new DirectorySoSource(updatedNativeLibDir, this.flags);
            this.soSource.prepare(this.flags);
            this.applicationContext = updatedContext;
            return true;
        }
        return false;
    }

    public Context getUpdatedContext() {
        try {
            return this.applicationContext.createPackageContext(this.applicationContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getNativeLibDirFromContext(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir);
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return this.soSource.loadLibrary(soName, loadFlags, threadPolicy);
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public File getSoFileByName(String soName) throws IOException {
        return this.soSource.getSoFileByName(soName);
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String getLibraryPath(String soName) throws IOException {
        return this.soSource.getLibraryPath(soName);
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public String[] getLibraryDependencies(String soName) throws IOException {
        return this.soSource.getLibraryDependencies(soName);
    }

    @Override // com.facebook.soloader.SoSource
    @Nullable
    public File unpackLibrary(String soName) throws IOException {
        return this.soSource.unpackLibrary(soName);
    }

    @Override // com.facebook.soloader.SoSource
    protected void prepare(int flags) throws IOException {
        this.soSource.prepare(flags);
    }

    @Override // com.facebook.soloader.SoSource
    public void addToLdLibraryPath(Collection<String> paths) {
        this.soSource.addToLdLibraryPath(paths);
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        return this.soSource.toString();
    }
}
