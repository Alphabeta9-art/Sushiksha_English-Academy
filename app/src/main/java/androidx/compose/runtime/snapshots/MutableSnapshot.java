package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.snapshots.tooling.SnapshotInstanceObservers;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserver;
import androidx.compose.runtime.snapshots.tooling.SnapshotObserverKt;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Snapshot.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0014\b\u0017\u0018\u0000 l2\u00020\u0001:\u0001lBI\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J8\u0010\u0013\u001a\u00020\u00002\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016J\u001e\u0010\u001d\u001a\u00020\u00012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bH\u0016J\u0015\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b J\u0015\u0010!\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\nH\u0010¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\nH\u0010¢\u0006\u0002\b&J\r\u0010'\u001a\u00020\nH\u0010¢\u0006\u0002\b(J\b\u0010)\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020\nH\u0002JG\u0010,\u001a\u00020\u00152\n\u0010-\u001a\u00060\u0003j\u0002`\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000203\u0018\u0001022\u0006\u00104\u001a\u00020\u0006H\u0000¢\u0006\u0004\b5\u00106J$\u00107\u001a\u0002H8\"\u0004\b\u0000\u001082\f\u00109\u001a\b\u0012\u0004\u0012\u0002H80:H\u0080\b¢\u0006\u0004\b;\u0010<J\r\u00107\u001a\u00020\nH\u0000¢\u0006\u0002\b;J\u001b\u0010=\u001a\u00020\n2\n\u0010>\u001a\u00060\u0003j\u0002`\u0004H\u0000¢\u0006\u0004\b?\u0010@J\u0015\u0010A\u001a\u00020\n2\u0006\u0010>\u001a\u00020BH\u0000¢\u0006\u0002\bCJ\u0015\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020FH\u0000¢\u0006\u0002\bGJ\b\u0010H\u001a\u00020\nH\u0002J\u0015\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\u0006H\u0000¢\u0006\u0002\bKJ\u0015\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u000200H\u0010¢\u0006\u0002\bNR\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010O\u001a\u00020BX\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\"\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/X\u0090\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\"\u0010X\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010YX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010^\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020FX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u000e\u0010J\u001a\u00020BX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010h\u001a\u00020\u0012X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u0018\"\u0004\bj\u0010k¨\u0006m"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot;", "Landroidx/compose/runtime/snapshots/Snapshot;", "snapshotId", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "invalid", "Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "readObserver", "Lkotlin/Function1;", "", "", "writeObserver", "<init>", "(JLandroidx/compose/runtime/snapshots/SnapshotIdSet;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getReadObserver$runtime", "()Lkotlin/jvm/functions/Function1;", "getWriteObserver$runtime", "hasPendingChanges", "", "takeNestedMutableSnapshot", "apply", "Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "readOnly", "getReadOnly", "()Z", "root", "getRoot", "()Landroidx/compose/runtime/snapshots/Snapshot;", "dispose", "takeNestedSnapshot", "nestedActivated", "snapshot", "nestedActivated$runtime", "nestedDeactivated", "nestedDeactivated$runtime", "notifyObjectsInitialized", "notifyObjectsInitialized$runtime", "closeLocked", "closeLocked$runtime", "releasePinnedSnapshotsForCloseLocked", "releasePinnedSnapshotsForCloseLocked$runtime", "validateNotApplied", "validateNotAppliedOrPinned", "abandon", "innerApplyLocked", "nextId", "modified", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/snapshots/StateObject;", "optimisticMerges", "", "Landroidx/compose/runtime/snapshots/StateRecord;", "invalidSnapshots", "innerApplyLocked$runtime", "(JLandroidx/collection/MutableScatterSet;Ljava/util/Map;Landroidx/compose/runtime/snapshots/SnapshotIdSet;)Landroidx/compose/runtime/snapshots/SnapshotApplyResult;", "advance", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "advance$runtime", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recordPrevious", "id", "recordPrevious$runtime", "(J)V", "recordPreviousPinnedSnapshot", "", "recordPreviousPinnedSnapshot$runtime", "recordPreviousPinnedSnapshots", "handles", "", "recordPreviousPinnedSnapshots$runtime", "releasePreviouslyPinnedSnapshotsLocked", "recordPreviousList", "snapshots", "recordPreviousList$runtime", "recordModified", "state", "recordModified$runtime", "writeCount", "getWriteCount$runtime", "()I", "setWriteCount$runtime", "(I)V", "getModified$runtime", "()Landroidx/collection/MutableScatterSet;", "setModified$runtime", "(Landroidx/collection/MutableScatterSet;)V", "merged", "", "getMerged$runtime", "()Ljava/util/List;", "setMerged$runtime", "(Ljava/util/List;)V", "previousIds", "getPreviousIds$runtime", "()Landroidx/compose/runtime/snapshots/SnapshotIdSet;", "setPreviousIds$runtime", "(Landroidx/compose/runtime/snapshots/SnapshotIdSet;)V", "previousPinnedSnapshots", "getPreviousPinnedSnapshots$runtime", "()[I", "setPreviousPinnedSnapshots$runtime", "([I)V", "applied", "getApplied$runtime", "setApplied$runtime", "(Z)V", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class MutableSnapshot extends Snapshot {
    private boolean applied;
    private List<? extends StateObject> merged;
    private MutableScatterSet<StateObject> modified;
    private SnapshotIdSet previousIds;
    private int[] previousPinnedSnapshots;
    private final Function1<Object, Unit> readObserver;
    private int snapshots;
    private int writeCount;
    private final Function1<Object, Unit> writeObserver;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final int[] EmptyIntArray = new int[0];

    public MutableSnapshot(long snapshotId, SnapshotIdSet invalid, Function1<Object, Unit> function1, Function1<Object, Unit> function2) {
        super(snapshotId, invalid, (DefaultConstructorMarker) null);
        this.readObserver = function1;
        this.writeObserver = function2;
        this.previousIds = SnapshotIdSet.INSTANCE.getEMPTY();
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getReadObserver$runtime */
    public Function1<Object, Unit> getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1<Object, Unit> getWriteObserver$runtime() {
        return this.writeObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean hasPendingChanges() {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        return modified$runtime != null && modified$runtime.isNotEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MutableSnapshot takeNestedMutableSnapshot$default(MutableSnapshot mutableSnapshot, Function1 function1, Function1 function2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: takeNestedMutableSnapshot");
        }
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        return mutableSnapshot.takeNestedMutableSnapshot(function1, function2);
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1<Object, Unit> readObserver, Function1<Object, Unit> writeObserver) {
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        Function1<Object, Unit> function1;
        Function1<Object, Unit> function2;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        MutableSnapshot parent$iv = this;
        PersistentList observers$iv = SnapshotObserverKt.observers;
        if (observers$iv == null) {
            second = null;
            function1 = readObserver;
            function2 = writeObserver;
        } else {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(observers$iv, parent$iv, false, readObserver, writeObserver);
            SnapshotInstanceObservers mappedObservers$iv = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = mappedObservers$iv.getReadObserver();
            Function1<Object, Unit> writeObserver2 = mappedObservers$iv.getWriteObserver();
            second = pairMergeObservers.getSecond();
            function1 = readObserver2;
            function2 = writeObserver2;
        }
        Function1<Object, Unit> function3 = function1;
        Function1<Object, Unit> function4 = function2;
        recordPrevious$runtime(getSnapshotId());
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                long newId = SnapshotKt.nextSnapshotId;
                long $this$plus$iv = SnapshotKt.nextSnapshotId;
                try {
                    SnapshotKt.nextSnapshotId = $this$plus$iv + ((long) 1);
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(newId);
                    SnapshotIdSet currentInvalid = getInvalid();
                    setInvalid$runtime(currentInvalid.set(newId));
                    long $this$plus$iv2 = getSnapshotId();
                    try {
                        try {
                            try {
                                Snapshot nestedMutableSnapshot = new NestedMutableSnapshot(newId, SnapshotKt.addRange(currentInvalid, $this$plus$iv2 + ((long) 1), newId), SnapshotKt.mergedReadObserver$default(function3, getReadObserver(), false, 4, null), SnapshotKt.mergedWriteObserver(function4, getWriteObserver$runtime()), this);
                                if (!getApplied() && !getDisposed()) {
                                    long previousId$iv = getSnapshotId();
                                    Object lock$iv$iv$iv = SnapshotKt.getLock();
                                    synchronized (lock$iv$iv$iv) {
                                        try {
                                            long j = SnapshotKt.nextSnapshotId;
                                            long $this$plus$iv$iv = SnapshotKt.nextSnapshotId;
                                            long previousId$iv2 = 1;
                                            try {
                                                SnapshotKt.nextSnapshotId = $this$plus$iv$iv + previousId$iv2;
                                                setSnapshotId$runtime(j);
                                                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                                                Unit unit = Unit.INSTANCE;
                                                long $this$plus$iv$iv2 = previousId$iv + ((long) 1);
                                                setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv$iv2, getSnapshotId()));
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    }
                                }
                                Snapshot result$iv = nestedMutableSnapshot;
                                if (observers$iv != null) {
                                    SnapshotObserverKt.dispatchCreatedObservers(observers$iv, parent$iv, result$iv, second);
                                }
                                return (MutableSnapshot) result$iv;
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:126:0x01a9 A[EDGE_INSN: B:126:0x01a9->B:71:0x01a9 BREAK  A[LOOP:2: B:52:0x014c->B:68:0x0199], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:19:0x0086 A[Catch: all -> 0x0269, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0269, blocks: (B:9:0x0035, B:28:0x00af, B:19:0x0086), top: B:120:0x0035 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0197 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:68:0x0199 A[Catch: all -> 0x025e, LOOP:2: B:52:0x014c->B:68:0x0199, LOOP_END, TryCatch #3 {all -> 0x025e, blocks: (B:63:0x0183, B:64:0x018e, B:74:0x01b7, B:76:0x01ca, B:78:0x01e4, B:80:0x01f0, B:85:0x01fd, B:86:0x020e, B:92:0x021d, B:96:0x0232, B:98:0x0236, B:100:0x0241, B:102:0x0251, B:68:0x0199), top: B:121:0x0183 }] */
    public SnapshotApplyResult apply() throws Throwable {
        Map<StateRecord, ? extends StateRecord> mapOptimisticMerges;
        MutableScatterSet<StateObject> mutableScatterSet;
        long j;
        long j2;
        MutableScatterSet previousModified;
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        if (modified$runtime != null) {
            GlobalSnapshot globalSnapshot = SnapshotKt.globalSnapshot;
            mapOptimisticMerges = SnapshotKt.optimisticMerges(globalSnapshot.getSnapshotId(), this, SnapshotKt.openSnapshots.clear(globalSnapshot.getSnapshotId()));
        } else {
            mapOptimisticMerges = null;
        }
        List listEmptyList = CollectionsKt.emptyList();
        Object globalModified = null;
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                SnapshotKt.validateOpen(this);
                if (modified$runtime != null) {
                    try {
                        if (modified$runtime.get_size() != 0) {
                            GlobalSnapshot globalSnapshot2 = SnapshotKt.globalSnapshot;
                            SnapshotApplyResult result = innerApplyLocked$runtime(SnapshotKt.nextSnapshotId, modified$runtime, mapOptimisticMerges, SnapshotKt.openSnapshots.clear(globalSnapshot2.getSnapshotId()));
                            if (!Intrinsics.areEqual(result, SnapshotApplyResult.Success.INSTANCE)) {
                                return result;
                            }
                            closeLocked$runtime();
                            Object previousModified2 = globalSnapshot2.getModified$runtime();
                            SnapshotKt.resetGlobalSnapshotLocked(globalSnapshot2, SnapshotKt.emptyLambda);
                            setModified$runtime(null);
                            globalSnapshot2.setModified$runtime(null);
                            globalModified = previousModified2;
                            listEmptyList = SnapshotKt.applyObservers;
                        } else {
                            closeLocked$runtime();
                            GlobalSnapshot globalSnapshot3 = SnapshotKt.globalSnapshot;
                            previousModified = globalSnapshot3.getModified$runtime();
                            SnapshotKt.resetGlobalSnapshotLocked(globalSnapshot3, SnapshotKt.emptyLambda);
                            if (previousModified != null && previousModified.isNotEmpty()) {
                                globalModified = previousModified;
                                listEmptyList = SnapshotKt.applyObservers;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } else {
                    closeLocked$runtime();
                    GlobalSnapshot globalSnapshot4 = SnapshotKt.globalSnapshot;
                    previousModified = globalSnapshot4.getModified$runtime();
                    SnapshotKt.resetGlobalSnapshotLocked(globalSnapshot4, SnapshotKt.emptyLambda);
                    if (previousModified != null) {
                        globalModified = previousModified;
                        listEmptyList = SnapshotKt.applyObservers;
                    }
                }
                Unit unit = Unit.INSTANCE;
                this.applied = true;
                if (globalModified != null) {
                    Set nonNullGlobalModified = ScatterSetWrapperKt.wrapIntoSet((ScatterSet) globalModified);
                    if (!nonNullGlobalModified.isEmpty()) {
                        List $this$fastForEach$iv = listEmptyList;
                        int size = $this$fastForEach$iv.size();
                        for (int index$iv = 0; index$iv < size; index$iv++) {
                            Object item$iv = $this$fastForEach$iv.get(index$iv);
                            Function2 it = (Function2) item$iv;
                            it.invoke(nonNullGlobalModified, this);
                        }
                    }
                }
                if (modified$runtime != null && modified$runtime.isNotEmpty()) {
                    Set modifiedSet = ScatterSetWrapperKt.wrapIntoSet(modified$runtime);
                    List $this$fastForEach$iv2 = listEmptyList;
                    int size2 = $this$fastForEach$iv2.size();
                    for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                        Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                        Function2 it2 = (Function2) item$iv2;
                        it2.invoke(modifiedSet, this);
                    }
                }
                SnapshotObserverKt.dispatchObserverOnApplied(this, modified$runtime);
                int $i$f$sync = 0;
                Object lock$iv$iv2 = SnapshotKt.getLock();
                synchronized (lock$iv$iv2) {
                    try {
                        releasePinnedSnapshotsForCloseLocked$runtime();
                        SnapshotKt.checkAndOverwriteUnusedRecordsLocked();
                        if (globalModified != null) {
                            ScatterSet this_$iv = (ScatterSet) globalModified;
                            j = 128;
                            Object[] elements$iv = this_$iv.elements;
                            j2 = 255;
                            long[] m$iv$iv = this_$iv.metadata;
                            int lastIndex$iv$iv = m$iv$iv.length - 2;
                            int i$iv$iv = 0;
                            if (0 <= lastIndex$iv$iv) {
                                while (true) {
                                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                                    int $i$f$sync2 = $i$f$sync;
                                    mutableScatterSet = modified$runtime;
                                    Map<StateRecord, ? extends StateRecord> map = mapOptimisticMerges;
                                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i$iv$iv != lastIndex$iv$iv) {
                                            break;
                                            break;
                                        }
                                        i$iv$iv++;
                                        modified$runtime = mutableScatterSet;
                                        mapOptimisticMerges = map;
                                        $i$f$sync = $i$f$sync2;
                                    } else {
                                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                        for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                                            long value$iv$iv$iv = slot$iv$iv & 255;
                                            if (value$iv$iv$iv < 128) {
                                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                                try {
                                                    StateObject it3 = (StateObject) elements$iv[index$iv$iv];
                                                    SnapshotKt.processForUnusedRecordsLocked(it3);
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    throw th;
                                                }
                                            }
                                            slot$iv$iv >>= 8;
                                        }
                                        if (bitCount$iv$iv != 8) {
                                            break;
                                        }
                                        if (i$iv$iv != lastIndex$iv$iv) {
                                            break;
                                        }
                                        i$iv$iv++;
                                        modified$runtime = mutableScatterSet;
                                        mapOptimisticMerges = map;
                                        $i$f$sync = $i$f$sync2;
                                    }
                                }
                            } else {
                                mutableScatterSet = modified$runtime;
                            }
                        } else {
                            mutableScatterSet = modified$runtime;
                            j = 128;
                            j2 = 255;
                        }
                        if (mutableScatterSet != null) {
                            ScatterSet this_$iv2 = mutableScatterSet;
                            int $i$f$forEach = 0;
                            Object[] elements$iv2 = this_$iv2.elements;
                            ScatterSet this_$iv$iv = this_$iv2;
                            int $i$f$forEachIndex = 0;
                            long[] m$iv$iv2 = this_$iv$iv.metadata;
                            int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
                            int i$iv$iv2 = 0;
                            if (0 <= lastIndex$iv$iv2) {
                                while (true) {
                                    long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                                    int $i$f$forEach2 = $i$f$forEach;
                                    Object[] elements$iv3 = elements$iv2;
                                    ScatterSet this_$iv$iv2 = this_$iv$iv;
                                    int $i$f$forEachIndex2 = $i$f$forEachIndex;
                                    if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                                        for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                                            long value$iv$iv$iv2 = slot$iv$iv2 & j2;
                                            if (value$iv$iv$iv2 < j) {
                                                int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv2;
                                                StateObject it4 = (StateObject) elements$iv3[index$iv$iv2];
                                                SnapshotKt.processForUnusedRecordsLocked(it4);
                                            }
                                            slot$iv$iv2 >>= 8;
                                        }
                                        if (bitCount$iv$iv2 != 8) {
                                            break;
                                        }
                                    }
                                    if (i$iv$iv2 == lastIndex$iv$iv2) {
                                        break;
                                    }
                                    i$iv$iv2++;
                                    this_$iv$iv = this_$iv$iv2;
                                    $i$f$forEachIndex = $i$f$forEachIndex2;
                                    $i$f$forEach = $i$f$forEach2;
                                    elements$iv2 = elements$iv3;
                                }
                            }
                        }
                        List<? extends StateObject> list = this.merged;
                        if (list != null) {
                            int size3 = list.size();
                            for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                                Object item$iv3 = list.get(index$iv3);
                                StateObject it5 = (StateObject) item$iv3;
                                SnapshotKt.processForUnusedRecordsLocked(it5);
                            }
                        }
                        this.merged = null;
                        Unit unit2 = Unit.INSTANCE;
                        return SnapshotApplyResult.Success.INSTANCE;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot getRoot() {
        return this;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (!getDisposed()) {
            super.dispose();
            mo4543nestedDeactivated$runtime(this);
            SnapshotObserverKt.dispatchObserverOnPreDispose(this);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1<Object, Unit> readObserver) throws Throwable {
        Function1<Object, Unit> function1;
        Map<SnapshotObserver, SnapshotInstanceObservers> second;
        validateNotDisposed$runtime();
        validateNotAppliedOrPinned();
        long previousId = getSnapshotId();
        Snapshot parent$iv = this instanceof GlobalSnapshot ? null : this;
        PersistentList observers$iv = SnapshotObserverKt.observers;
        if (observers$iv == null) {
            function1 = readObserver;
            second = null;
        } else {
            Pair<SnapshotInstanceObservers, Map<SnapshotObserver, SnapshotInstanceObservers>> pairMergeObservers = SnapshotObserverKt.mergeObservers(observers$iv, parent$iv, true, readObserver, null);
            SnapshotInstanceObservers mappedObservers$iv = pairMergeObservers.getFirst();
            Function1<Object, Unit> readObserver2 = mappedObservers$iv.getReadObserver();
            mappedObservers$iv.getWriteObserver();
            function1 = readObserver2;
            second = pairMergeObservers.getSecond();
        }
        Function1<Object, Unit> function2 = function1;
        recordPrevious$runtime(getSnapshotId());
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                long readonlyId = SnapshotKt.nextSnapshotId;
                long $this$plus$iv = SnapshotKt.nextSnapshotId;
                long previousId2 = 1;
                try {
                    SnapshotKt.nextSnapshotId = $this$plus$iv + previousId2;
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(readonlyId);
                    try {
                        Snapshot nestedReadonlySnapshot = new NestedReadonlySnapshot(readonlyId, SnapshotKt.addRange(getInvalid(), previousId + ((long) 1), readonlyId), SnapshotKt.mergedReadObserver$default(function2, getReadObserver(), false, 4, null), this);
                        if (!getApplied() && !getDisposed()) {
                            long previousId$iv = getSnapshotId();
                            Object lock$iv$iv$iv = SnapshotKt.getLock();
                            synchronized (lock$iv$iv$iv) {
                                try {
                                    long j = SnapshotKt.nextSnapshotId;
                                    long $this$plus$iv$iv = SnapshotKt.nextSnapshotId;
                                    long previousId$iv2 = 1;
                                    try {
                                        SnapshotKt.nextSnapshotId = $this$plus$iv$iv + previousId$iv2;
                                        setSnapshotId$runtime(j);
                                        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                                        Unit unit = Unit.INSTANCE;
                                        long $this$plus$iv$iv2 = previousId$iv + ((long) 1);
                                        setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv$iv2, getSnapshotId()));
                                    } catch (Throwable th) {
                                        th = th;
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                        }
                        Snapshot result$iv = nestedReadonlySnapshot;
                        if (observers$iv != null) {
                            SnapshotObserverKt.dispatchCreatedObservers(observers$iv, parent$iv, result$iv, second);
                        }
                        return result$iv;
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedActivated$runtime */
    public void mo4542nestedActivated$runtime(Snapshot snapshot) {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: nestedDeactivated$runtime */
    public void mo4543nestedDeactivated$runtime(Snapshot snapshot) {
        boolean value$iv = this.snapshots > 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        this.snapshots--;
        if (this.snapshots == 0 && !this.applied) {
            abandon();
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime() {
        if (this.applied || getDisposed()) {
            return;
        }
        advance$runtime();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void closeLocked$runtime() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getSnapshotId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void releasePinnedSnapshotsForCloseLocked$runtime() {
        releasePreviouslyPinnedSnapshotsLocked();
        super.releasePinnedSnapshotsForCloseLocked$runtime();
    }

    private final void validateNotApplied() {
        boolean value$iv = !this.applied;
        if (value$iv) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
    }

    private final void validateNotAppliedOrPinned() {
        boolean value$iv = true;
        if (this.applied) {
            MutableSnapshot this_$iv = this;
            if (!(((Snapshot) this_$iv).pinningTrackingHandle >= 0)) {
                value$iv = false;
            }
        }
        if (value$iv) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
    }

    private final void abandon() {
        ScatterSet this_$iv;
        int j$iv$iv;
        MutableSnapshot mutableSnapshot = this;
        MutableScatterSet modified = mutableSnapshot.getModified$runtime();
        if (modified != null) {
            mutableSnapshot.validateNotApplied();
            mutableSnapshot.setModified$runtime(null);
            long id = mutableSnapshot.getSnapshotId();
            MutableScatterSet this_$iv2 = modified;
            Object[] elements$iv = this_$iv2.elements;
            long[] m$iv$iv = this_$iv2.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    MutableScatterSet modified2 = modified;
                    long id2 = id;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        this_$iv = this_$iv2;
                    } else {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv2 = 0;
                        while (j$iv$iv2 < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                                StateObject state = (StateObject) elements$iv[index$iv$iv];
                                StateRecord current = state.getFirstStateRecord();
                                while (current != null) {
                                    if (current.getSnapshotId() != id2) {
                                        j$iv$iv = j$iv$iv2;
                                        if (!CollectionsKt.contains(mutableSnapshot.previousIds, Long.valueOf(current.getSnapshotId()))) {
                                        }
                                        current = current.getNext();
                                        this_$iv2 = this_$iv2;
                                        j$iv$iv2 = j$iv$iv;
                                        mutableSnapshot = this;
                                    } else {
                                        j$iv$iv = j$iv$iv2;
                                    }
                                    current.setSnapshotId$runtime(SnapshotKt.INVALID_SNAPSHOT);
                                    current = current.getNext();
                                    this_$iv2 = this_$iv2;
                                    j$iv$iv2 = j$iv$iv;
                                    mutableSnapshot = this;
                                }
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv2++;
                            this_$iv2 = this_$iv2;
                            i = i;
                            mutableSnapshot = this;
                        }
                        this_$iv = this_$iv2;
                        if (bitCount$iv$iv != i) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv2 = this_$iv;
                    modified = modified2;
                    id = id2;
                    mutableSnapshot = this;
                }
            }
        }
        closeAndReleasePinning$runtime();
    }

    /* JADX WARN: Code duplicated, block: B:74:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:76:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:78:0x021d  */
    /* JADX WARN: Code duplicated, block: B:87:0x023c  */
    /* JADX WARN: Code duplicated, block: B:89:0x024b A[LOOP:3: B:88:0x0249->B:89:0x024b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:92:0x025f  */
    /* JADX WARN: Code duplicated, block: B:93:0x0261  */
    public final SnapshotApplyResult innerApplyLocked$runtime(long nextId, MutableScatterSet<StateObject> modified, Map<StateRecord, ? extends StateRecord> optimisticMerges, SnapshotIdSet invalidSnapshots) throws Throwable {
        List list;
        ArrayList arrayList;
        ArrayList arrayList2;
        int index$iv;
        int size;
        List<? extends StateObject> list2;
        List<? extends StateObject> listPlus;
        List it;
        int size2;
        int index$iv2;
        StateObject state;
        StateRecord stateRecord;
        Object lock$iv$iv;
        SnapshotIdSet start;
        ScatterSet this_$iv$iv;
        int $i$f$forEachIndex;
        SnapshotIdSet start2;
        int i;
        int j$iv$iv;
        ScatterSet this_$iv$iv2;
        int $i$f$forEachIndex2;
        StateRecord previous;
        StateRecord merged;
        List it2;
        List it3;
        ArrayList arrayList3;
        List list3 = null;
        SnapshotIdSet start3 = getInvalid().set(getSnapshotId()).or(this.previousIds);
        ArrayList arrayList4 = null;
        MutableScatterSet<StateObject> this_$iv = modified;
        int $i$f$forEach = 0;
        Object[] elements$iv = this_$iv.elements;
        ScatterSet this_$iv$iv3 = this_$iv;
        int $i$f$forEachIndex3 = 0;
        long[] m$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                arrayList = arrayList4;
                ScatterSet this_$iv2 = this_$iv;
                int $i$f$forEach2 = $i$f$forEach;
                Object[] elements$iv2 = elements$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv2 = 0;
                    while (j$iv$iv2 < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                            i = i2;
                            StateObject state2 = (StateObject) elements$iv2[index$iv$iv];
                            List list4 = list3;
                            StateRecord first = state2.getFirstStateRecord();
                            j$iv$iv = j$iv$iv2;
                            StateRecord current = SnapshotKt.readable(first, nextId, invalidSnapshots);
                            if (current == null || (previous = SnapshotKt.readable(first, getSnapshotId(), start3)) == null || previous.getSnapshotId() == SnapshotId_jvmKt.toSnapshotId(1)) {
                                start2 = start3;
                                this_$iv$iv2 = this_$iv$iv3;
                                $i$f$forEachIndex2 = $i$f$forEachIndex3;
                                list3 = list4;
                            } else {
                                if (Intrinsics.areEqual(current, previous)) {
                                    start2 = start3;
                                    this_$iv$iv2 = this_$iv$iv3;
                                    $i$f$forEachIndex2 = $i$f$forEachIndex3;
                                } else {
                                    this_$iv$iv2 = this_$iv$iv3;
                                    $i$f$forEachIndex2 = $i$f$forEachIndex3;
                                    long snapshotId = getSnapshotId();
                                    start2 = start3;
                                    SnapshotIdSet start4 = getInvalid();
                                    StateRecord applied = SnapshotKt.readable(first, snapshotId, start4);
                                    if (applied == null) {
                                        SnapshotKt.readError();
                                        throw new KotlinNothingValueException();
                                    }
                                    if (optimisticMerges == null || (merged = optimisticMerges.get(current)) == null) {
                                        merged = state2.mergeRecords(previous, current, applied);
                                    }
                                    if (merged == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!Intrinsics.areEqual(merged, applied)) {
                                        if (Intrinsics.areEqual(merged, current)) {
                                            if (list4 == null) {
                                                it3 = new ArrayList();
                                                list4 = it3;
                                            } else {
                                                it3 = list4;
                                            }
                                            it3.add(TuplesKt.to(state2, current.create(getSnapshotId())));
                                            if (arrayList == null) {
                                                arrayList3 = new ArrayList();
                                                arrayList = arrayList3;
                                            } else {
                                                arrayList3 = arrayList;
                                            }
                                            arrayList3.add(state2);
                                            list3 = list4;
                                        } else {
                                            if (list4 == null) {
                                                it2 = new ArrayList();
                                                list4 = it2;
                                            } else {
                                                it2 = list4;
                                            }
                                            it2.add(!Intrinsics.areEqual(merged, previous) ? TuplesKt.to(state2, merged) : TuplesKt.to(state2, previous.create(getSnapshotId())));
                                            list3 = list4;
                                        }
                                    }
                                }
                                list3 = list4;
                            }
                        } else {
                            start2 = start3;
                            i = i2;
                            j$iv$iv = j$iv$iv2;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndex2 = $i$f$forEachIndex3;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv2 = j$iv$iv + 1;
                        optimisticMerges = optimisticMerges;
                        this_$iv$iv3 = this_$iv$iv2;
                        i2 = i;
                        $i$f$forEachIndex3 = $i$f$forEachIndex2;
                        start3 = start2;
                    }
                    list = list3;
                    start = start3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndex = $i$f$forEachIndex3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                    list3 = list;
                    arrayList4 = arrayList;
                } else {
                    start = start3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndex = $i$f$forEachIndex3;
                    arrayList4 = arrayList;
                }
                if (i$iv$iv != lastIndex$iv$iv) {
                    i$iv$iv++;
                    $i$f$forEach = $i$f$forEach2;
                    elements$iv = elements$iv2;
                    this_$iv = this_$iv2;
                    this_$iv$iv3 = this_$iv$iv;
                    $i$f$forEachIndex3 = $i$f$forEachIndex;
                    start3 = start;
                }
            }
            if (list != null) {
                it = list;
                advance$runtime();
                size2 = it.size();
                for (index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                    Object item$iv = it.get(index$iv2);
                    Pair merged2 = (Pair) item$iv;
                    state = (StateObject) merged2.component1();
                    stateRecord = (StateRecord) merged2.component2();
                    stateRecord.setSnapshotId$runtime(nextId);
                    lock$iv$iv = SnapshotKt.getLock();
                    synchronized (lock$iv$iv) {
                        stateRecord.setNext$runtime(state.getFirstStateRecord());
                        state.prependStateRecord(stateRecord);
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            if (arrayList != null) {
                arrayList2 = arrayList;
                size = arrayList2.size();
                for (index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv2 = arrayList2.get(index$iv);
                    modified.remove((StateObject) item$iv2);
                }
                list2 = this.merged;
                if (list2 == null) {
                    listPlus = arrayList2;
                } else {
                    listPlus = CollectionsKt.plus((Collection) list2, (Iterable) arrayList2);
                }
                this.merged = listPlus;
            }
            return SnapshotApplyResult.Success.INSTANCE;
        }
        list = list3;
        arrayList = arrayList4;
        if (list != null) {
            it = list;
            advance$runtime();
            size2 = it.size();
            while (index$iv2 < size2) {
                Object item$iv3 = it.get(index$iv2);
                Pair merged3 = (Pair) item$iv3;
                state = (StateObject) merged3.component1();
                stateRecord = (StateRecord) merged3.component2();
                stateRecord.setSnapshotId$runtime(nextId);
                lock$iv$iv = SnapshotKt.getLock();
                synchronized (lock$iv$iv) {
                    stateRecord.setNext$runtime(state.getFirstStateRecord());
                    state.prependStateRecord(stateRecord);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        if (arrayList != null) {
            arrayList2 = arrayList;
            size = arrayList2.size();
            while (index$iv < size) {
                Object item$iv4 = arrayList2.get(index$iv);
                modified.remove((StateObject) item$iv4);
            }
            list2 = this.merged;
            if (list2 == null) {
                listPlus = arrayList2;
            } else {
                listPlus = CollectionsKt.plus((Collection) list2, (Iterable) arrayList2);
            }
            this.merged = listPlus;
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    public final <T> T advance$runtime(Function0<? extends T> block) throws Throwable {
        recordPrevious$runtime(getSnapshotId());
        T tInvoke = block.invoke();
        if (getApplied() || getDisposed()) {
            return tInvoke;
        }
        long previousId = getSnapshotId();
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            try {
                long j = SnapshotKt.nextSnapshotId;
                long $this$plus$iv = SnapshotKt.nextSnapshotId;
                try {
                    SnapshotKt.nextSnapshotId = $this$plus$iv + ((long) 1);
                    setSnapshotId$runtime(j);
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                    Unit unit = Unit.INSTANCE;
                    long $this$plus$iv2 = previousId + ((long) 1);
                    setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv2, getSnapshotId()));
                    return tInvoke;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void advance$runtime() throws Throwable {
        recordPrevious$runtime(getSnapshotId());
        Unit unit = Unit.INSTANCE;
        if (getApplied() || getDisposed()) {
            return;
        }
        long previousId$iv = getSnapshotId();
        Object lock$iv$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv$iv) {
            try {
                long j = SnapshotKt.nextSnapshotId;
                long $this$plus$iv$iv = SnapshotKt.nextSnapshotId;
                try {
                    SnapshotKt.nextSnapshotId = $this$plus$iv$iv + ((long) 1);
                    setSnapshotId$runtime(j);
                    SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getSnapshotId());
                    Unit unit2 = Unit.INSTANCE;
                    long $this$plus$iv$iv2 = previousId$iv + ((long) 1);
                    setInvalid$runtime(SnapshotKt.addRange(getInvalid(), $this$plus$iv$iv2, getSnapshotId()));
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final void recordPrevious$runtime(long id) {
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            this.previousIds = this.previousIds.set(id);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void recordPreviousPinnedSnapshot$runtime(int id) {
        if (id >= 0) {
            this.previousPinnedSnapshots = ArraysKt.plus(this.previousPinnedSnapshots, id);
        }
    }

    public final void recordPreviousPinnedSnapshots$runtime(int[] handles) {
        if (handles.length == 0) {
            return;
        }
        int[] pinned = this.previousPinnedSnapshots;
        this.previousPinnedSnapshots = pinned.length == 0 ? handles : ArraysKt.plus(pinned, handles);
    }

    private final void releasePreviouslyPinnedSnapshotsLocked() {
        int length = this.previousPinnedSnapshots.length;
        for (int index = 0; index < length; index++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[index]);
        }
    }

    public final void recordPreviousList$runtime(SnapshotIdSet snapshots) {
        Object lock$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv) {
            this.previousIds = this.previousIds.or(snapshots);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: recordModified$runtime */
    public void mo4544recordModified$runtime(StateObject state) {
        MutableScatterSet<StateObject> modified$runtime = getModified$runtime();
        if (modified$runtime == null) {
            modified$runtime = ScatterSetKt.mutableScatterSetOf();
            setModified$runtime(modified$runtime);
        }
        modified$runtime.add(state);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* JADX INFO: renamed from: getWriteCount$runtime, reason: from getter */
    public int getWriteCount() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime(int i) {
        this.writeCount = i;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public MutableScatterSet<StateObject> getModified$runtime() {
        return this.modified;
    }

    public void setModified$runtime(MutableScatterSet<StateObject> mutableScatterSet) {
        this.modified = mutableScatterSet;
    }

    public final List<StateObject> getMerged$runtime() {
        return this.merged;
    }

    public final void setMerged$runtime(List<? extends StateObject> list) {
        this.merged = list;
    }

    /* JADX INFO: renamed from: getPreviousIds$runtime, reason: from getter */
    public final SnapshotIdSet getPreviousIds() {
        return this.previousIds;
    }

    public final void setPreviousIds$runtime(SnapshotIdSet snapshotIdSet) {
        this.previousIds = snapshotIdSet;
    }

    /* JADX INFO: renamed from: getPreviousPinnedSnapshots$runtime, reason: from getter */
    public final int[] getPreviousPinnedSnapshots() {
        return this.previousPinnedSnapshots;
    }

    public final void setPreviousPinnedSnapshots$runtime(int[] iArr) {
        this.previousPinnedSnapshots = iArr;
    }

    /* JADX INFO: renamed from: getApplied$runtime, reason: from getter */
    public final boolean getApplied() {
        return this.applied;
    }

    public final void setApplied$runtime(boolean z) {
        this.applied = z;
    }

    /* JADX INFO: compiled from: Snapshot.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/compose/runtime/snapshots/MutableSnapshot$Companion;", "", "<init>", "()V", "EmptyIntArray", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
