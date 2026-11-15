package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SyncFailedException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public abstract class UnpackingSoSource extends DirectorySoSource {
    private static final String DEPS_FILE_NAME = "dso_deps";
    private static final String INSTANCE_LOCK_FILE_NAME = "dso_instance_lock";
    private static final String LOCK_FILE_NAME = "dso_lock";
    private static final String MANIFEST_FILE_NAME = "dso_manifest";
    private static final byte MANIFEST_VERSION = 1;
    protected static final byte STATE_CLEAN = 1;
    protected static final byte STATE_DIRTY = 0;
    private static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";

    @Nullable
    private String[] mAbis;
    protected final Context mContext;

    @Nullable
    protected String mCorruptedLib;

    @Nullable
    protected FileLocker mInstanceLock;
    private final Map<String, Object> mLibsBeingLoaded;

    protected interface InputDso extends Closeable {
        int available() throws IOException;

        Dso getDso();

        String getFileName();

        InputStream getStream();

        void write(DataOutput dataOutput, byte[] bArr) throws IOException;
    }

    protected abstract Unpacker makeUnpacker(byte b) throws IOException;

    protected UnpackingSoSource(Context context, String name) {
        super(getSoStorePath(context, name), 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    protected UnpackingSoSource(Context context, File storePath) {
        super(storePath, 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String name) {
        return new File(context.getApplicationInfo().dataDir + DomExceptionUtils.SEPARATOR + name);
    }

    @Override // com.facebook.soloader.SoSource
    public String[] getSoSourceAbis() {
        if (this.mAbis == null) {
            return super.getSoSourceAbis();
        }
        return this.mAbis;
    }

    public void setSoSourceAbis(String[] abis) {
        this.mAbis = abis;
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String name, String hash) {
            this.name = name;
            this.hash = hash;
        }
    }

    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsos) {
            this.dsos = dsos;
        }

        static final DsoManifest read(DataInput xdi) throws IOException {
            int version = xdi.readByte();
            if (version != 1) {
                throw new RuntimeException("wrong dso manifest version");
            }
            int nrDso = xdi.readInt();
            if (nrDso < 0) {
                throw new RuntimeException("illegal number of shared libraries");
            }
            Dso[] dsos = new Dso[nrDso];
            for (int i = 0; i < nrDso; i++) {
                dsos[i] = new Dso(xdi.readUTF(), xdi.readUTF());
            }
            return new DsoManifest(dsos);
        }

        public final void write(DataOutput xdo) throws IOException {
            xdo.writeByte(1);
            xdo.writeInt(this.dsos.length);
            for (int i = 0; i < this.dsos.length; i++) {
                xdo.writeUTF(this.dsos[i].name);
                xdo.writeUTF(this.dsos[i].hash);
            }
        }
    }

    public static class InputDsoStream implements InputDso {
        private final InputStream content;
        private final Dso dso;

        public InputDsoStream(Dso dso, InputStream content) {
            this.dso = dso;
            this.content = content;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public void write(DataOutput out, byte[] ioBuffer) throws IOException {
            SysUtil.copyBytes(out, this.content, Integer.MAX_VALUE, ioBuffer);
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public Dso getDso() {
            return this.dso;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public String getFileName() {
            return this.dso.name;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public int available() throws IOException {
            return this.content.available();
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public InputStream getStream() {
            return this.content;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    protected static abstract class InputDsoIterator implements Closeable {
        public abstract boolean hasNext();

        @Nullable
        public abstract InputDso next() throws IOException;

        protected InputDsoIterator() {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    protected static abstract class Unpacker implements Closeable {
        public abstract DsoManifest getDsoManifest() throws IOException;

        public abstract InputDsoIterator openDsoIterator() throws IOException;

        protected Unpacker() {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeState(File stateFileName, byte state) throws IOException {
        try {
            RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
            try {
                stateFile.seek(0L);
                stateFile.write(state);
                stateFile.setLength(stateFile.getFilePointer());
                stateFile.getFD().sync();
                stateFile.close();
            } catch (Throwable th) {
                try {
                    stateFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (SyncFailedException e) {
            Log.w(TAG, "state file sync failed", e);
        }
    }

    protected String getSoNameFromFileName(String fileName) {
        return fileName;
    }

    private void deleteUnmentionedFiles(Dso[] dsos) throws IOException {
        String[] existingFiles = this.soDirectory.list();
        if (existingFiles == null) {
            throw new IOException("unable to list directory " + this.soDirectory);
        }
        for (String fileName : existingFiles) {
            if (!fileName.equals(STATE_FILE_NAME) && !fileName.equals(LOCK_FILE_NAME) && !fileName.equals(INSTANCE_LOCK_FILE_NAME) && !fileName.equals(DEPS_FILE_NAME) && !fileName.equals(MANIFEST_FILE_NAME)) {
                boolean found = false;
                for (int j = 0; !found && j < dsos.length; j++) {
                    if (dsos[j].name.equals(getSoNameFromFileName(fileName))) {
                        found = true;
                    }
                }
                if (!found) {
                    File fileNameToDelete = new File(this.soDirectory, fileName);
                    Log.v(TAG, "deleting unaccounted-for file " + fileNameToDelete);
                    SysUtil.dumbDeleteRecursive(fileNameToDelete);
                }
            }
        }
    }

    private void extractDso(InputDso iDso, byte[] ioBuffer) throws IOException {
        Log.i(TAG, "extracting DSO " + iDso.getDso().name);
        try {
            if (!this.soDirectory.setWritable(true)) {
                throw new IOException("cannot make directory writable for us: " + this.soDirectory);
            }
            extractDsoImpl(iDso, ioBuffer);
            if (!this.soDirectory.setWritable(false)) {
                Log.w(TAG, "error removing " + this.soDirectory.getCanonicalPath() + " write permission");
            }
        } catch (Throwable th) {
            if (!this.soDirectory.setWritable(false)) {
                Log.w(TAG, "error removing " + this.soDirectory.getCanonicalPath() + " write permission");
            }
            throw th;
        }
    }

    private void extractDsoImpl(InputDso iDso, byte[] ioBuffer) throws IOException {
        File dsoFileName = new File(this.soDirectory, iDso.getFileName());
        RandomAccessFile dsoFile = null;
        try {
            try {
                if (dsoFileName.exists() && !dsoFileName.setWritable(true)) {
                    Log.w(TAG, "error adding write permission to: " + dsoFileName);
                }
                try {
                    RandomAccessFile dsoFile2 = new RandomAccessFile(dsoFileName, "rw");
                    dsoFile = dsoFile2;
                } catch (IOException ex) {
                    Log.w(TAG, "error overwriting " + dsoFileName + " trying to delete and start over", ex);
                    SysUtil.dumbDeleteRecursive(dsoFileName);
                    dsoFile = new RandomAccessFile(dsoFileName, "rw");
                }
                int sizeHint = iDso.available();
                if (sizeHint > 1) {
                    SysUtil.fallocateIfSupported(dsoFile.getFD(), sizeHint);
                }
                iDso.write(dsoFile, ioBuffer);
                dsoFile.setLength(dsoFile.getFilePointer());
                if (!dsoFileName.setExecutable(true, false)) {
                    throw new IOException("cannot make file executable: " + dsoFileName);
                }
                if (!dsoFileName.setWritable(false)) {
                    Log.w(TAG, "error removing " + dsoFileName + " write permission");
                }
                dsoFile.close();
            } catch (IOException e) {
                SysUtil.dumbDeleteRecursive(dsoFileName);
                throw e;
            }
        } catch (Throwable e2) {
            if (!dsoFileName.setWritable(false)) {
                Log.w(TAG, "error removing " + dsoFileName + " write permission");
            }
            if (dsoFile != null) {
                dsoFile.close();
            }
            throw e2;
        }
    }

    private void regenerate(byte state, DsoManifest desiredManifest, InputDsoIterator dsoIterator) throws IOException {
        Log.v(TAG, "regenerating DSO store " + getClass().getName());
        File manifestFileName = new File(this.soDirectory, MANIFEST_FILE_NAME);
        RandomAccessFile manifestFile = new RandomAccessFile(manifestFileName, "rw");
        DsoManifest existingManifest = null;
        if (state == 1) {
            try {
                try {
                    existingManifest = DsoManifest.read(manifestFile);
                } catch (Exception ex) {
                    Log.i(TAG, "error reading existing DSO manifest", ex);
                }
            } catch (Throwable th) {
                try {
                    manifestFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (existingManifest == null) {
            existingManifest = new DsoManifest(new Dso[0]);
        }
        deleteUnmentionedFiles(desiredManifest.dsos);
        byte[] ioBuffer = new byte[32768];
        while (dsoIterator.hasNext()) {
            InputDso iDso = dsoIterator.next();
            boolean obsolete = true;
            int i = 0;
            while (obsolete) {
                try {
                    if (i >= existingManifest.dsos.length) {
                        break;
                    }
                    String iDsoName = iDso.getDso().name;
                    if (existingManifest.dsos[i].name.equals(iDsoName) && existingManifest.dsos[i].hash.equals(iDso.getDso().hash)) {
                        obsolete = false;
                    }
                    i++;
                } catch (Throwable th3) {
                    if (iDso != null) {
                        try {
                            iDso.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                    }
                    throw th3;
                }
            }
            File dsoFile = new File(this.soDirectory, iDso.getFileName());
            if (!dsoFile.exists()) {
                obsolete = true;
            }
            if (obsolete) {
                extractDso(iDso, ioBuffer);
            }
            if (iDso != null) {
                iDso.close();
            }
        }
        manifestFile.close();
        Log.v(TAG, "Finished regenerating DSO store " + getClass().getName());
    }

    protected boolean depsChanged(byte[] existingDeps, byte[] deps) {
        return !Arrays.equals(existingDeps, deps);
    }

    protected boolean refreshLocked(FileLocker lock, int flags, byte[] deps) throws Throwable {
        byte state;
        Throwable th;
        byte state2;
        DsoManifest desiredManifest;
        File stateFileName = new File(this.soDirectory, STATE_FILE_NAME);
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        try {
            byte state3 = stateFile.readByte();
            if (state3 != 1) {
                Log.v(TAG, "dso store " + this.soDirectory + " regeneration interrupted: wiping clean");
                state3 = 0;
            }
            state = state3;
        } catch (EOFException e) {
            state = 0;
        } catch (Throwable th2) {
            try {
                stateFile.close();
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
                throw th2;
            }
        }
        stateFile.close();
        File depsFileName = new File(this.soDirectory, DEPS_FILE_NAME);
        RandomAccessFile depsFile = new RandomAccessFile(depsFileName, "rw");
        try {
            byte[] existingDeps = new byte[(int) depsFile.length()];
            if (depsFile.read(existingDeps) != existingDeps.length) {
                Log.v(TAG, "short read of so store deps file: marking unclean");
                state = 0;
            }
            if (depsChanged(existingDeps, deps)) {
                Log.v(TAG, "deps mismatch on deps store: regenerating");
                state2 = 0;
            } else {
                state2 = state;
            }
            if (state2 == 0 || (flags & 2) != 0) {
                try {
                    Log.v(TAG, "so store dirty: regenerating");
                    writeState(stateFileName, (byte) 0);
                    Unpacker u = makeUnpacker(state2);
                    try {
                        DsoManifest desiredManifest2 = u.getDsoManifest();
                        InputDsoIterator idi = u.openDsoIterator();
                        try {
                            regenerate(state2, desiredManifest2, idi);
                            if (idi != null) {
                                idi.close();
                            }
                            if (u != null) {
                                u.close();
                            }
                            desiredManifest = desiredManifest2;
                        } catch (Throwable th4) {
                            if (idi == null) {
                                throw th4;
                            }
                            try {
                                idi.close();
                                throw th4;
                            } catch (Throwable th5) {
                                th4.addSuppressed(th5);
                                throw th4;
                            }
                            th = th;
                            try {
                                depsFile.close();
                                throw th;
                            } catch (Throwable th6) {
                                th.addSuppressed(th6);
                                throw th;
                            }
                        }
                    } catch (Throwable th7) {
                        if (u == null) {
                            throw th7;
                        }
                        try {
                            u.close();
                            throw th7;
                        } catch (Throwable th8) {
                            th7.addSuppressed(th8);
                            throw th7;
                        }
                    }
                } catch (Throwable th9) {
                    th = th9;
                    depsFile.close();
                    throw th;
                }
            } else {
                desiredManifest = null;
            }
            depsFile.close();
            if (desiredManifest == null) {
                return false;
            }
            DsoManifest manifest = desiredManifest;
            if ((flags & 4) != 0) {
                return true;
            }
            Runnable syncer = createSyncer(lock, deps, stateFileName, depsFileName, manifest, false);
            if ((flags & 1) != 0) {
                new Thread(syncer, "SoSync:" + this.soDirectory.getName()).start();
            } else {
                syncer.run();
            }
            return true;
        } catch (Throwable th10) {
            th = th10;
        }
    }

    private Runnable createSyncer(final FileLocker lock, final byte[] deps, final File stateFileName, final File depsFileName, final DsoManifest manifest, final Boolean quietly) {
        return new Runnable() { // from class: com.facebook.soloader.UnpackingSoSource.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    try {
                        Log.v(UnpackingSoSource.TAG, "starting syncer worker");
                        RandomAccessFile depsFile = new RandomAccessFile(depsFileName, "rw");
                        try {
                            depsFile.write(deps);
                            depsFile.setLength(depsFile.getFilePointer());
                            depsFile.close();
                            File manifestFileName = new File(UnpackingSoSource.this.soDirectory, UnpackingSoSource.MANIFEST_FILE_NAME);
                            RandomAccessFile manifestFile = new RandomAccessFile(manifestFileName, "rw");
                            try {
                                manifest.write(manifestFile);
                                manifestFile.close();
                                SysUtil.fsyncRecursive(UnpackingSoSource.this.soDirectory);
                                UnpackingSoSource.writeState(stateFileName, (byte) 1);
                                Log.v(UnpackingSoSource.TAG, "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                                lock.close();
                            } catch (Throwable th) {
                                try {
                                    manifestFile.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            try {
                                depsFile.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                            throw th3;
                        }
                    } catch (Throwable th5) {
                        Log.v(UnpackingSoSource.TAG, "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                        lock.close();
                        throw th5;
                    }
                } catch (IOException ex) {
                    if (!quietly.booleanValue()) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };
    }

    protected byte[] getDepsBlock() throws IOException {
        Parcel parcel = Parcel.obtain();
        Unpacker u = makeUnpacker((byte) 1);
        try {
            Dso[] dsos = u.getDsoManifest().dsos;
            parcel.writeByte((byte) 1);
            parcel.writeInt(dsos.length);
            for (int i = 0; i < dsos.length; i++) {
                parcel.writeString(dsos[i].name);
                parcel.writeString(dsos[i].hash);
            }
            if (u != null) {
                u.close();
            }
            byte[] depsBlock = parcel.marshall();
            parcel.recycle();
            return depsBlock;
        } catch (Throwable th) {
            if (u != null) {
                try {
                    u.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Nullable
    protected FileLocker getOrCreateLock(File lockFileName, boolean blocking) throws IOException {
        return SysUtil.getOrCreateLockOnDir(this.soDirectory, lockFileName, blocking);
    }

    @Override // com.facebook.soloader.SoSource
    protected void prepare(int flags) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        boolean dirCanWrite = this.soDirectory.canWrite();
        FileLocker lock = null;
        if (!dirCanWrite) {
            try {
                if (!this.soDirectory.setWritable(true)) {
                    Log.w(TAG, "error adding " + this.soDirectory.getCanonicalPath() + " write permission");
                }
            } finally {
                if (!dirCanWrite && !this.soDirectory.setWritable(false)) {
                    Log.w(TAG, "error removing " + this.soDirectory.getCanonicalPath() + " write permission");
                }
                if (lock != null) {
                    Log.v(TAG, "releasing dso store lock for " + this.soDirectory);
                    lock.close();
                } else {
                    Log.v(TAG, "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
                }
            }
        }
        File lockFileName = new File(this.soDirectory, LOCK_FILE_NAME);
        lock = getOrCreateLock(lockFileName, true);
        if (this.mInstanceLock == null) {
            File instanceLockFileName = new File(this.soDirectory, INSTANCE_LOCK_FILE_NAME);
            this.mInstanceLock = getOrCreateLock(instanceLockFileName, false);
        }
        Log.v(TAG, "locked dso store " + this.soDirectory);
        if (refreshLocked(lock, flags, getDepsBlock())) {
            lock = null;
        } else {
            Log.i(TAG, "dso store is up-to-date: " + this.soDirectory);
        }
    }

    private Object getLibraryLock(String soName) {
        Object lock;
        synchronized (this.mLibsBeingLoaded) {
            lock = this.mLibsBeingLoaded.get(soName);
            if (lock == null) {
                lock = new Object();
                this.mLibsBeingLoaded.put(soName, lock);
            }
        }
        return lock;
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    @Nullable
    public String getLibraryPath(String soName) throws IOException {
        File soFile = getSoFileByName(soName);
        if (soFile == null) {
            return null;
        }
        return soFile.getCanonicalPath();
    }

    protected synchronized void prepare(String soName) throws IOException {
        Object lock = getLibraryLock(soName);
        synchronized (lock) {
            try {
                this.mCorruptedLib = soName;
                prepare(2);
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int iLoadLibraryFrom;
        Object lock = getLibraryLock(soName);
        synchronized (lock) {
            iLoadLibraryFrom = loadLibraryFrom(soName, loadFlags, this.soDirectory, threadPolicy);
        }
        return iLoadLibraryFrom;
    }
}
