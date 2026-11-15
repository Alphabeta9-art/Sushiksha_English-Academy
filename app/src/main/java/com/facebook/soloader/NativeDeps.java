package com.facebook.soloader;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public final class NativeDeps {
    private static final float HASHMAP_LOAD_FACTOR = 1.0f;
    private static final int INITIAL_HASH = 5381;
    private static final String LOG_TAG = "NativeDeps";
    private static final int WAITING_THREADS_WARNING_THRESHOLD = 3;

    @Nullable
    private static byte[] sEncodedDeps;
    private static Map<Integer, List<Integer>> sPrecomputedDeps;
    private static List<Integer> sPrecomputedLibs;
    private static final int LIB_PREFIX_LEN = "lib".length();
    private static final int LIB_SUFFIX_LEN = ".so".length();
    private static final int LIB_PREFIX_SUFFIX_LEN = LIB_PREFIX_LEN + LIB_SUFFIX_LEN;
    private static volatile boolean sInitialized = false;
    private static volatile boolean sUseDepsFileAsync = false;
    private static final ReentrantReadWriteLock sWaitForDepsFileLock = new ReentrantReadWriteLock();

    public static String[] getDependencies(String soName, File elfFile) throws IOException {
        String[] deps = awaitGetDepsFromPrecomputedDeps(soName);
        if (deps != null) {
            return deps;
        }
        return MinElf.extract_DT_NEEDED(elfFile);
    }

    public static String[] getDependencies(String soName, ElfByteChannel bc) throws IOException {
        String[] deps = awaitGetDepsFromPrecomputedDeps(soName);
        if (deps != null) {
            return deps;
        }
        return MinElf.extract_DT_NEEDED(bc);
    }

    @Nullable
    private static String[] awaitGetDepsFromPrecomputedDeps(String soName) {
        if (sInitialized) {
            return tryGetDepsFromPrecomputedDeps(soName);
        }
        if (sUseDepsFileAsync) {
            sWaitForDepsFileLock.readLock().lock();
            try {
                return tryGetDepsFromPrecomputedDeps(soName);
            } finally {
                sWaitForDepsFileLock.readLock().unlock();
            }
        }
        return null;
    }

    public static boolean useDepsFile(final Context context, boolean async, final boolean extractToDisk) {
        if (!async) {
            return useDepsFileFromApkSync(context, extractToDisk);
        }
        Runnable runnable = new Runnable() { // from class: com.facebook.soloader.NativeDeps.1
            @Override // java.lang.Runnable
            public void run() {
                NativeDeps.sWaitForDepsFileLock.writeLock().lock();
                boolean unused = NativeDeps.sUseDepsFileAsync = true;
                try {
                    NativeDeps.useDepsFileFromApkSync(context, extractToDisk);
                } finally {
                    int waitingThreads = NativeDeps.sWaitForDepsFileLock.getReadLockCount();
                    if (waitingThreads >= 3) {
                        Log.w(NativeDeps.LOG_TAG, "NativeDeps initialization finished with " + Integer.toString(waitingThreads) + " threads waiting.");
                    }
                    NativeDeps.sWaitForDepsFileLock.writeLock().unlock();
                    boolean unused2 = NativeDeps.sUseDepsFileAsync = false;
                }
            }
        };
        new Thread(runnable, "soloader-nativedeps-init").start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean useDepsFileFromApkSync(Context context, boolean extractToDisk) throws IOException {
        boolean success;
        try {
            success = initDeps(context, extractToDisk);
        } catch (IOException e) {
            success = false;
        }
        if (!success && extractToDisk) {
            try {
                NativeDepsUnpacker.ensureNativeDepsAvailable(context);
                success = initDeps(context, extractToDisk);
            } catch (IOException e2) {
            }
        }
        if (!success) {
            Log.w(LOG_TAG, "Failed to extract native deps from APK, falling back to using MinElf to get library dependencies.");
        }
        return success;
    }

    private static boolean initDeps(Context context, boolean extractToDisk) throws IOException {
        byte[] depsBytes;
        verifyUninitialized();
        byte[] apkId = null;
        if (extractToDisk) {
            File apkFile = new File(context.getApplicationInfo().sourceDir);
            apkId = SysUtil.makeApkDepBlock(apkFile, context);
            depsBytes = NativeDepsUnpacker.readNativeDepsFromDisk(context);
        } else {
            depsBytes = NativeDepsUnpacker.readNativeDepsFromApk(context);
        }
        return processDepsBytes(apkId, depsBytes);
    }

    private static void indexLib(int hash, int nameBegin) {
        sPrecomputedLibs.add(Integer.valueOf(nameBegin));
        List<Integer> bucket = sPrecomputedDeps.get(Integer.valueOf(hash));
        if (bucket == null) {
            bucket = new ArrayList();
            sPrecomputedDeps.put(Integer.valueOf(hash), bucket);
        }
        bucket.add(Integer.valueOf(nameBegin));
    }

    private static void indexDepsBytes(byte[] bytes, int offset) {
        int nextByte;
        boolean inLibName = true;
        int byteOffset = offset;
        while (true) {
            if (inLibName) {
                int libNameBegin = byteOffset;
                int libHash = INITIAL_HASH;
                while (true) {
                    try {
                        nextByte = bytes[byteOffset];
                        if (nextByte <= 32) {
                            break;
                        }
                        libHash = (libHash << 5) + libHash + nextByte;
                        byteOffset++;
                    } catch (IndexOutOfBoundsException e) {
                        if (inLibName && libNameBegin != bytes.length) {
                            indexLib(libHash, libNameBegin);
                            return;
                        }
                        return;
                    }
                }
                indexLib(libHash, libNameBegin);
                boolean inLibName2 = nextByte != 32;
                inLibName = inLibName2;
            } else {
                while (bytes[byteOffset] != 10) {
                    byteOffset++;
                }
                inLibName = true;
            }
            byteOffset++;
        }
    }

    private static int verifyBytesAndGetOffset(@Nullable byte[] apkId, @Nullable byte[] bytes) {
        if (apkId == null || apkId.length == 0 || bytes.length < apkId.length + 4) {
            return -1;
        }
        int depsLen = ByteBuffer.wrap(bytes, apkId.length, 4).getInt();
        if (bytes.length != apkId.length + 4 + depsLen) {
            return -1;
        }
        for (int i = 0; i < apkId.length; i++) {
            if (apkId[i] != bytes[i]) {
                return -1;
            }
        }
        return apkId.length + 4;
    }

    private static int findNextLine(byte[] bytes, int offset) {
        while (offset < bytes.length && bytes[offset] != 10) {
            offset++;
        }
        if (offset < bytes.length) {
            return offset + 1;
        }
        return offset;
    }

    private static int parseLibCount(byte[] data, int offset, int length) {
        try {
            return Integer.parseInt(new String(data, offset, length));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static boolean processDepsBytes(byte[] apkId, byte[] deps) throws IOException {
        int deps_offset;
        int libsCount;
        int offset = 0;
        if ((apkId != null && (offset = verifyBytesAndGetOffset(apkId, deps)) == -1) || (deps_offset = findNextLine(deps, offset)) >= deps.length || (libsCount = parseLibCount(deps, offset, (deps_offset - offset) - 1)) <= 0) {
            return false;
        }
        sPrecomputedDeps = new HashMap(((int) (libsCount / 1.0f)) + 1, 1.0f);
        sPrecomputedLibs = new ArrayList(libsCount);
        indexDepsBytes(deps, deps_offset);
        if (sPrecomputedLibs.size() != libsCount) {
            return false;
        }
        sEncodedDeps = deps;
        sInitialized = true;
        return true;
    }

    private static boolean libIsAtOffset(String soName, int offset) {
        int i = LIB_PREFIX_LEN;
        for (int j = offset; i < soName.length() - LIB_SUFFIX_LEN && j < sEncodedDeps.length && (soName.codePointAt(i) & 255) == sEncodedDeps[j]; j++) {
            i++;
        }
        return i == soName.length() - LIB_SUFFIX_LEN;
    }

    private static int hashLib(String soName) {
        int libHash = INITIAL_HASH;
        for (int i = LIB_PREFIX_LEN; i < soName.length() - LIB_SUFFIX_LEN; i++) {
            libHash = (libHash << 5) + libHash + soName.codePointAt(i);
        }
        return libHash;
    }

    private static int getOffsetForLib(String soName) {
        int libHash = hashLib(soName);
        List<Integer> bucket = sPrecomputedDeps.get(Integer.valueOf(libHash));
        if (bucket == null) {
            return -1;
        }
        Iterator<Integer> it = bucket.iterator();
        while (it.hasNext()) {
            int offset = it.next().intValue();
            if (libIsAtOffset(soName, offset)) {
                return offset;
            }
        }
        return -1;
    }

    @Nullable
    private static String getLibString(int depIndex) {
        if (depIndex >= sPrecomputedLibs.size()) {
            return null;
        }
        int initialOffset = sPrecomputedLibs.get(depIndex).intValue();
        int byteOffset = initialOffset;
        while (byteOffset < sEncodedDeps.length && sEncodedDeps[byteOffset] > 32) {
            byteOffset++;
        }
        int libNameLength = (byteOffset - initialOffset) + LIB_PREFIX_SUFFIX_LEN;
        char[] libBytes = new char[libNameLength];
        libBytes[0] = 'l';
        libBytes[1] = 'i';
        libBytes[2] = 'b';
        for (int i = 0; i < libNameLength - LIB_PREFIX_SUFFIX_LEN; i++) {
            libBytes[LIB_PREFIX_LEN + i] = (char) sEncodedDeps[initialOffset + i];
        }
        int i2 = libNameLength - 3;
        libBytes[i2] = '.';
        libBytes[libNameLength - 2] = 's';
        libBytes[libNameLength - 1] = 'o';
        return new String(libBytes);
    }

    @Nullable
    private static String[] getDepsForLibAtOffset(int offset, int libNameLength) {
        int nextByte;
        List<String> deps = new ArrayList<>();
        int depIndex = 0;
        boolean hasDep = false;
        int depsOffset = (offset + libNameLength) - LIB_PREFIX_SUFFIX_LEN;
        for (int byteOffset = depsOffset; byteOffset < sEncodedDeps.length && (nextByte = sEncodedDeps[byteOffset]) != 10; byteOffset++) {
            if (nextByte == 32) {
                if (hasDep) {
                    String dep = getLibString(depIndex);
                    if (dep == null) {
                        return null;
                    }
                    deps.add(dep);
                    hasDep = false;
                    depIndex = 0;
                } else {
                    continue;
                }
            } else {
                if (nextByte < 48 || nextByte > 57) {
                    return null;
                }
                hasDep = true;
                depIndex = (depIndex * 10) + (nextByte - 48);
            }
        }
        if (hasDep) {
            String dep2 = getLibString(depIndex);
            if (dep2 == null) {
                return null;
            }
            deps.add(dep2);
        }
        if (deps.size() == 0) {
            return null;
        }
        String[] depsArray = new String[deps.size()];
        return (String[]) deps.toArray(depsArray);
    }

    @Nullable
    static String[] tryGetDepsFromPrecomputedDeps(String soName) {
        int offset;
        if (sInitialized && soName.length() > LIB_PREFIX_SUFFIX_LEN && (offset = getOffsetForLib(soName)) != -1) {
            return getDepsForLibAtOffset(offset, soName.length());
        }
        return null;
    }

    private static void verifyUninitialized() {
        if (!sInitialized) {
            return;
        }
        synchronized (NativeDeps.class) {
            if (sInitialized) {
                throw new IllegalStateException("Trying to initialize NativeDeps but it was already initialized");
            }
        }
    }
}
