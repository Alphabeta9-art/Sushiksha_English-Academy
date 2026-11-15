package androidx.media3.exoplayer.offline;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheKeyFactory;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.datasource.cache.ContentMetadata;
import androidx.media3.exoplayer.offline.FilterableManifest;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes11.dex */
public abstract class SegmentDownloader<M extends FilterableManifest<M>> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    public static final long DEFAULT_MAX_MERGED_SEGMENT_START_TIME_DIFF_MS = 20000;
    private final ArrayList<RunnableFutureTask<?, ?>> activeRunnables;
    private final Cache cache;
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final CacheKeyFactory cacheKeyFactory;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final DataSpec manifestDataSpec;
    private final ParsingLoadable.Parser<M> manifestParser;
    private final long maxMergedSegmentStartTimeDiffUs;
    private final PriorityTaskManager priorityTaskManager;
    private final ArrayList<StreamKey> streamKeys;

    protected abstract List<Segment> getSegments(DataSource dataSource, M m, boolean z) throws InterruptedException, IOException;

    protected static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long startTimeUs, DataSpec dataSpec) {
            this.startTimeUs = startTimeUs;
            this.dataSpec = dataSpec;
        }

        @Override // java.lang.Comparable
        public int compareTo(Segment other) {
            return Util.compareLong(this.startTimeUs, other.startTimeUs);
        }
    }

    @Deprecated
    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> manifestParser, CacheDataSource.Factory cacheDataSourceFactory, Executor executor) {
        this(mediaItem, manifestParser, cacheDataSourceFactory, executor, 20000L);
    }

    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> manifestParser, CacheDataSource.Factory cacheDataSourceFactory, Executor executor, long maxMergedSegmentStartTimeDiffMs) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        this.manifestDataSpec = getCompressibleDataSpec(mediaItem.localConfiguration.uri);
        this.manifestParser = manifestParser;
        this.streamKeys = new ArrayList<>(mediaItem.localConfiguration.streamKeys);
        this.cacheDataSourceFactory = cacheDataSourceFactory;
        this.executor = executor;
        this.cache = (Cache) Assertions.checkNotNull(cacheDataSourceFactory.getCache());
        this.cacheKeyFactory = cacheDataSourceFactory.getCacheKeyFactory();
        this.priorityTaskManager = cacheDataSourceFactory.getUpstreamPriorityTaskManager();
        this.activeRunnables = new ArrayList<>();
        this.maxMergedSegmentStartTimeDiffUs = Util.msToUs(maxMergedSegmentStartTimeDiffMs);
    }

    /* JADX WARN: Code duplicated, block: B:110:0x00be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:27:0x00af A[Catch: all -> 0x01e3, TryCatch #0 {all -> 0x01e3, blocks: (B:6:0x001a, B:8:0x0030, B:10:0x003b, B:15:0x0072, B:17:0x007a, B:19:0x0088, B:23:0x0098, B:27:0x00af, B:30:0x00b8, B:32:0x00be, B:37:0x00d2, B:39:0x00db, B:40:0x00df, B:42:0x00e3, B:44:0x00e9, B:46:0x00ed, B:47:0x00f4, B:49:0x00fa, B:51:0x0111, B:53:0x0133, B:55:0x0143, B:72:0x018e, B:58:0x014e, B:60:0x0158, B:66:0x0165, B:68:0x017b, B:69:0x0187, B:71:0x018b, B:73:0x0195, B:74:0x0198, B:75:0x0199, B:50:0x0105), top: B:100:0x001a }] */
    /* JADX WARN: Code duplicated, block: B:30:0x00b8 A[Catch: all -> 0x01e3, TryCatch #0 {all -> 0x01e3, blocks: (B:6:0x001a, B:8:0x0030, B:10:0x003b, B:15:0x0072, B:17:0x007a, B:19:0x0088, B:23:0x0098, B:27:0x00af, B:30:0x00b8, B:32:0x00be, B:37:0x00d2, B:39:0x00db, B:40:0x00df, B:42:0x00e3, B:44:0x00e9, B:46:0x00ed, B:47:0x00f4, B:49:0x00fa, B:51:0x0111, B:53:0x0133, B:55:0x0143, B:72:0x018e, B:58:0x014e, B:60:0x0158, B:66:0x0165, B:68:0x017b, B:69:0x0187, B:71:0x018b, B:73:0x0195, B:74:0x0198, B:75:0x0199, B:50:0x0105), top: B:100:0x001a }] */
    /* JADX WARN: Code duplicated, block: B:31:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:92:0x01ed A[LOOP:1: B:90:0x01e5->B:92:0x01ed, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:95:0x0206 A[LOOP:2: B:94:0x0204->B:95:0x0206, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:98:0x021b  */
    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void download(Downloader.ProgressListener progressListener) throws Throwable {
        int i;
        int size;
        CacheDataSource cacheDataSourceCreateDataSourceForDownloading;
        byte[] bArr;
        byte[] bArr2;
        SegmentDownloadRunnable segmentDownloadRunnable;
        Segment segment;
        long j;
        long cachedBytes;
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayDeque arrayDeque2 = new ArrayDeque();
        if (this.priorityTaskManager != null) {
            this.priorityTaskManager.add(-1000);
        }
        boolean z = true;
        try {
            CacheDataSource cacheDataSourceCreateDataSourceForDownloading2 = this.cacheDataSourceFactory.createDataSourceForDownloading();
            FilterableManifest manifest = getManifest(cacheDataSourceCreateDataSourceForDownloading2, this.manifestDataSpec, false);
            List<Segment> segments = getSegments(cacheDataSourceCreateDataSourceForDownloading2, !this.streamKeys.isEmpty() ? (FilterableManifest) manifest.copy(this.streamKeys) : manifest, false);
            Collections.sort(segments);
            mergeSegments(segments, this.cacheKeyFactory, this.maxMergedSegmentStartTimeDiffUs);
            int size2 = segments.size();
            int i2 = 0;
            int size3 = segments.size() - 1;
            long j2 = 0;
            long j3 = 0;
            while (size3 >= 0) {
                try {
                    DataSpec dataSpec = segments.get(size3).dataSpec;
                    String strBuildCacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
                    boolean z2 = z;
                    CacheDataSource cacheDataSource = cacheDataSourceCreateDataSourceForDownloading2;
                    long j4 = dataSpec.length;
                    if (j4 == -1) {
                        long contentLength = ContentMetadata.getContentLength(this.cache.getContentMetadata(strBuildCacheKey));
                        if (contentLength != -1) {
                            j = contentLength - dataSpec.position;
                        }
                        cachedBytes = this.cache.getCachedBytes(strBuildCacheKey, dataSpec.position, j);
                        j2 += cachedBytes;
                        if (j != -1) {
                            if (j == cachedBytes) {
                                i2++;
                                segments.remove(size3);
                            }
                            if (j3 != -1) {
                                j3 += j;
                            }
                        } else {
                            j3 = -1;
                        }
                        size3--;
                        z = z2;
                        cacheDataSourceCreateDataSourceForDownloading2 = cacheDataSource;
                    }
                    j = j4;
                    cachedBytes = this.cache.getCachedBytes(strBuildCacheKey, dataSpec.position, j);
                    j2 += cachedBytes;
                    if (j != -1) {
                        if (j == cachedBytes) {
                            i2++;
                            segments.remove(size3);
                        }
                        if (j3 != -1) {
                            j3 += j;
                        }
                    } else {
                        j3 = -1;
                    }
                    size3--;
                    z = z2;
                    cacheDataSourceCreateDataSourceForDownloading2 = cacheDataSource;
                } catch (Throwable th) {
                    th = th;
                    for (i = 0; i < this.activeRunnables.size(); i++) {
                        this.activeRunnables.get(i).cancel(true);
                    }
                    for (size = this.activeRunnables.size() - 1; size >= 0; size--) {
                        this.activeRunnables.get(size).blockUntilFinished();
                        removeActiveRunnable(size);
                    }
                    if (this.priorityTaskManager != null) {
                        this.priorityTaskManager.remove(-1000);
                    }
                    throw th;
                }
            }
            boolean z3 = z;
            ProgressNotifier progressNotifier = progressListener != null ? new ProgressNotifier(progressListener, j3, size2, j2, i2) : null;
            arrayDeque.addAll(segments);
            while (!this.isCanceled && !arrayDeque.isEmpty()) {
                if (this.priorityTaskManager != null) {
                    this.priorityTaskManager.proceed(-1000);
                }
                if (arrayDeque2.isEmpty()) {
                    cacheDataSourceCreateDataSourceForDownloading = this.cacheDataSourceFactory.createDataSourceForDownloading();
                    bArr = new byte[131072];
                } else {
                    SegmentDownloadRunnable segmentDownloadRunnable2 = (SegmentDownloadRunnable) arrayDeque2.removeFirst();
                    cacheDataSourceCreateDataSourceForDownloading = segmentDownloadRunnable2.dataSource;
                    bArr = segmentDownloadRunnable2.temporaryBuffer;
                }
                Segment segment2 = (Segment) arrayDeque.removeFirst();
                SegmentDownloadRunnable segmentDownloadRunnable3 = new SegmentDownloadRunnable(segment2, cacheDataSourceCreateDataSourceForDownloading, progressNotifier, bArr);
                addActiveRunnable(segmentDownloadRunnable3);
                this.executor.execute(segmentDownloadRunnable3);
                int size4 = this.activeRunnables.size() - 1;
                while (size4 >= 0) {
                    SegmentDownloadRunnable segmentDownloadRunnable4 = (SegmentDownloadRunnable) this.activeRunnables.get(size4);
                    if (arrayDeque.isEmpty() || segmentDownloadRunnable4.isDone()) {
                        try {
                            segmentDownloadRunnable4.get();
                            removeActiveRunnable(size4);
                            bArr2 = bArr;
                            segmentDownloadRunnable = segmentDownloadRunnable4;
                            try {
                                arrayDeque2.addLast(segmentDownloadRunnable);
                                segment = segment2;
                            } catch (ExecutionException e) {
                                e = e;
                                Throwable th2 = (Throwable) Assertions.checkNotNull(e.getCause());
                                segment = segment2;
                                if (!(th2 instanceof PriorityTaskManager.PriorityTooLowException)) {
                                    if (th2 instanceof IOException) {
                                        throw ((IOException) th2);
                                    }
                                    Util.sneakyThrow(th2);
                                    while (i < this.activeRunnables.size()) {
                                        this.activeRunnables.get(i).cancel(true);
                                    }
                                    while (size >= 0) {
                                        this.activeRunnables.get(size).blockUntilFinished();
                                        removeActiveRunnable(size);
                                    }
                                    if (this.priorityTaskManager != null) {
                                        this.priorityTaskManager.remove(-1000);
                                    }
                                    throw th;
                                }
                                arrayDeque.addFirst(segmentDownloadRunnable.segment);
                                removeActiveRunnable(size4);
                                arrayDeque2.addLast(segmentDownloadRunnable);
                            }
                        } catch (ExecutionException e2) {
                            e = e2;
                            bArr2 = bArr;
                            segmentDownloadRunnable = segmentDownloadRunnable4;
                        }
                    } else {
                        bArr2 = bArr;
                        segment = segment2;
                    }
                    size4--;
                    bArr = bArr2;
                    segment2 = segment;
                }
                segmentDownloadRunnable3.blockUntilStarted();
            }
            for (int i3 = 0; i3 < this.activeRunnables.size(); i3++) {
                this.activeRunnables.get(i3).cancel(z3);
            }
            for (int size5 = this.activeRunnables.size() - (z3 ? 1 : 0); size5 >= 0; size5--) {
                this.activeRunnables.get(size5).blockUntilFinished();
                removeActiveRunnable(size5);
            }
            if (this.priorityTaskManager != null) {
                this.priorityTaskManager.remove(-1000);
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void cancel() {
        synchronized (this.activeRunnables) {
            this.isCanceled = true;
            for (int i = 0; i < this.activeRunnables.size(); i++) {
                this.activeRunnables.get(i).cancel(true);
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void remove() {
        CacheDataSource dataSource = this.cacheDataSourceFactory.createDataSourceForRemovingDownload();
        try {
            List<Segment> segments = getSegments(dataSource, getManifest(dataSource, this.manifestDataSpec, true), true);
            for (int i = 0; i < segments.size(); i++) {
                this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(segments.get(i).dataSpec));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e2) {
        } finally {
            this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(this.manifestDataSpec));
        }
    }

    protected final M getManifest(final DataSource dataSource, final DataSpec dataSpec, boolean removing) throws InterruptedException, IOException {
        return (M) execute(new RunnableFutureTask<M, IOException>() { // from class: androidx.media3.exoplayer.offline.SegmentDownloader.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.media3.common.util.RunnableFutureTask
            public M doWork() throws IOException {
                return (M) ParsingLoadable.load(dataSource, SegmentDownloader.this.manifestParser, dataSpec, 4);
            }
        }, removing);
    }

    protected final <T> T execute(RunnableFutureTask<T, ?> runnable, boolean removing) throws Throwable {
        if (removing) {
            runnable.run();
            try {
                return runnable.get();
            } catch (ExecutionException e) {
                Throwable cause = (Throwable) Assertions.checkNotNull(e.getCause());
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                Util.sneakyThrow(e);
            }
        }
        while (!this.isCanceled) {
            if (this.priorityTaskManager != null) {
                this.priorityTaskManager.proceed(-1000);
            }
            addActiveRunnable(runnable);
            this.executor.execute(runnable);
            try {
                T t = runnable.get();
                runnable.blockUntilFinished();
                removeActiveRunnable((RunnableFutureTask<?, ?>) runnable);
                return t;
            } catch (ExecutionException e2) {
                try {
                    Throwable cause2 = (Throwable) Assertions.checkNotNull(e2.getCause());
                    if (!(cause2 instanceof PriorityTaskManager.PriorityTooLowException)) {
                        if (cause2 instanceof IOException) {
                            throw ((IOException) cause2);
                        }
                        Util.sneakyThrow(e2);
                    }
                    runnable.blockUntilFinished();
                    removeActiveRunnable((RunnableFutureTask<?, ?>) runnable);
                } catch (Throwable e3) {
                    runnable.blockUntilFinished();
                    removeActiveRunnable((RunnableFutureTask<?, ?>) runnable);
                    throw e3;
                }
            }
        }
        throw new InterruptedException();
    }

    protected static DataSpec getCompressibleDataSpec(Uri uri) {
        return new DataSpec.Builder().setUri(uri).setFlags(1).build();
    }

    private <T> void addActiveRunnable(RunnableFutureTask<T, ?> runnable) throws InterruptedException {
        synchronized (this.activeRunnables) {
            if (this.isCanceled) {
                throw new InterruptedException();
            }
            this.activeRunnables.add(runnable);
        }
    }

    private void removeActiveRunnable(RunnableFutureTask<?, ?> runnable) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(runnable);
        }
    }

    private void removeActiveRunnable(int index) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(index);
        }
    }

    private static void mergeSegments(List<Segment> segments, CacheKeyFactory keyFactory, long maxMergedSegmentStartTimeDiffUs) {
        HashMap<String, Integer> lastIndexByCacheKey = new HashMap<>();
        int nextOutIndex = 0;
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            String cacheKey = keyFactory.buildCacheKey(segment.dataSpec);
            Integer lastIndex = lastIndexByCacheKey.get(cacheKey);
            Segment lastSegment = lastIndex == null ? null : segments.get(lastIndex.intValue());
            if (lastSegment == null || segment.startTimeUs > lastSegment.startTimeUs + maxMergedSegmentStartTimeDiffUs || !canMergeSegments(lastSegment.dataSpec, segment.dataSpec)) {
                lastIndexByCacheKey.put(cacheKey, Integer.valueOf(nextOutIndex));
                segments.set(nextOutIndex, segment);
                nextOutIndex++;
            } else {
                long mergedLength = segment.dataSpec.length != -1 ? segment.dataSpec.length + lastSegment.dataSpec.length : -1L;
                DataSpec mergedDataSpec = lastSegment.dataSpec.subrange(0L, mergedLength);
                segments.set(((Integer) Assertions.checkNotNull(lastIndex)).intValue(), new Segment(lastSegment.startTimeUs, mergedDataSpec));
            }
        }
        int i2 = segments.size();
        Util.removeRange(segments, nextOutIndex, i2);
    }

    private static boolean canMergeSegments(DataSpec dataSpec1, DataSpec dataSpec2) {
        return dataSpec1.uri.equals(dataSpec2.uri) && dataSpec1.length != -1 && dataSpec1.position + dataSpec1.length == dataSpec2.position && Util.areEqual(dataSpec1.key, dataSpec2.key) && dataSpec1.flags == dataSpec2.flags && dataSpec1.httpMethod == dataSpec2.httpMethod && dataSpec1.httpRequestHeaders.equals(dataSpec2.httpRequestHeaders);
    }

    private static final class SegmentDownloadRunnable extends RunnableFutureTask<Void, IOException> {
        private final CacheWriter cacheWriter;
        public final CacheDataSource dataSource;
        private final ProgressNotifier progressNotifier;
        public final Segment segment;
        public final byte[] temporaryBuffer;

        public SegmentDownloadRunnable(Segment segment, CacheDataSource dataSource, ProgressNotifier progressNotifier, byte[] temporaryBuffer) {
            this.segment = segment;
            this.dataSource = dataSource;
            this.progressNotifier = progressNotifier;
            this.temporaryBuffer = temporaryBuffer;
            this.cacheWriter = new CacheWriter(dataSource, segment.dataSpec, temporaryBuffer, progressNotifier);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.media3.common.util.RunnableFutureTask
        public Void doWork() throws IOException {
            this.cacheWriter.cache();
            if (this.progressNotifier != null) {
                this.progressNotifier.onSegmentDownloaded();
                return null;
            }
            return null;
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        protected void cancelWork() {
            this.cacheWriter.cancel();
        }
    }

    private static final class ProgressNotifier implements CacheWriter.ProgressListener {
        private long bytesDownloaded;
        private final long contentLength;
        private final Downloader.ProgressListener progressListener;
        private int segmentsDownloaded;
        private final int totalSegments;

        public ProgressNotifier(Downloader.ProgressListener progressListener, long contentLength, int totalSegments, long bytesDownloaded, int segmentsDownloaded) {
            this.progressListener = progressListener;
            this.contentLength = contentLength;
            this.totalSegments = totalSegments;
            this.bytesDownloaded = bytesDownloaded;
            this.segmentsDownloaded = segmentsDownloaded;
        }

        @Override // androidx.media3.datasource.cache.CacheWriter.ProgressListener
        public void onProgress(long requestLength, long bytesCached, long newBytesCached) {
            this.bytesDownloaded += newBytesCached;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }

        public void onSegmentDownloaded() {
            this.segmentsDownloaded++;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }

        private float getPercentDownloaded() {
            if (this.contentLength != -1 && this.contentLength != 0) {
                return (this.bytesDownloaded * 100.0f) / this.contentLength;
            }
            if (this.totalSegments != 0) {
                return (this.segmentsDownloaded * 100.0f) / this.totalSegments;
            }
            return -1.0f;
        }
    }
}
