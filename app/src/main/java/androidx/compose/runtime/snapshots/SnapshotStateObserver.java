package androidx.compose.runtime.snapshots;

import androidx.autofill.HintConstants;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: SnapshotStateObserver.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0001<B0\u0012'\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0005H\u0002J\u0016\u0010\u0017\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0002J\u0010\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u001d\u0010#\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u0003H\u0082\bJ\u001d\u0010%\u001a\u00020\u00052\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00100\u0003H\u0082\bJ?\u0010,\u001a\u00020\u0005\"\b\b\u0000\u0010-*\u00020\u00012\u0006\u0010.\u001a\u0002H-2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u00100J\u0016\u00101\u001a\u00020\u00052\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u000e\u00102\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0001J)\u00103\u001a\u00020\u00052!\u00104\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00100\u0003J\u0006\u00105\u001a\u00020\u0005J\u0006\u00106\u001a\u00020\u0005J\u001e\u00107\u001a\u00020\u00052\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\u0006\u00109\u001a\u00020\u0014H\u0007J\u0006\u00102\u001a\u00020\u0005J&\u0010:\u001a\u00020\u001f\"\b\b\u0000\u0010-*\u00020\u00012\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H-\u0012\u0004\u0012\u00020\u00050\u0003H\u0002R/\u0010\u0002\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00060\u0001j\u0002`!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "", "onChangedExecutor", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "callback", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "pendingChanges", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "sendingNotifications", "", "applyObserver", "Lkotlin/Function2;", "", "Landroidx/compose/runtime/snapshots/Snapshot;", "drainChanges", "sendNotifications", "addChanges", "set", "removeChanges", "report", "", "readObserver", "observedScopeMaps", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "observedScopeMapsLock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "forEachScopeMap", "block", "removeScopeMapIf", "applyUnsubscribe", "Landroidx/compose/runtime/snapshots/ObserverHandle;", "isPaused", "currentMap", "currentMapThreadId", "", "observeReads", ExifInterface.GPS_DIRECTION_TRUE, "scope", "onValueChangedForScope", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "withNoObservations", "clear", "clearIf", "predicate", TtmlNode.START, "stop", "notifyChanges", "changes", "snapshot", "ensureMap", "onChanged", "ObservedScopeMap", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotStateObserver {
    public static final int $stable = 8;
    private ObserverHandle applyUnsubscribe;
    private ObservedScopeMap currentMap;
    private boolean isPaused;
    private final Function1<Function0<Unit>, Unit> onChangedExecutor;
    private boolean sendingNotifications;
    private final AtomicReference<Object> pendingChanges = new AtomicReference<>(null);
    private final Function2<Set<? extends Object>, Snapshot, Unit> applyObserver = new Function2() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return SnapshotStateObserver.applyObserver$lambda$0(this.f$0, (Set) obj, (Snapshot) obj2);
        }
    };
    private final Function1<Object, Unit> readObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return SnapshotStateObserver.readObserver$lambda$0(this.f$0, obj);
        }
    };
    private final MutableVector<ObservedScopeMap> observedScopeMaps = new MutableVector<>(new ObservedScopeMap[16], 0);
    private final Object observedScopeMapsLock = new Object();
    private long currentMapThreadId = -1;

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(Function1<? super Function0<Unit>, Unit> function1) {
        this.onChangedExecutor = function1;
    }

    static final Unit applyObserver$lambda$0(SnapshotStateObserver this$0, Set applied, Snapshot snapshot) {
        this$0.addChanges(applied);
        if (this$0.drainChanges()) {
            this$0.sendNotifications();
        }
        return Unit.INSTANCE;
    }

    private final boolean drainChanges() {
        boolean z;
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            z = this.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean hasValues = false;
        while (true) {
            Set<? extends Object> setRemoveChanges = removeChanges();
            if (setRemoveChanges == null) {
                return hasValues;
            }
            Object lock$iv$iv = this.observedScopeMapsLock;
            synchronized (lock$iv$iv) {
                MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
                Object[] content$iv$iv = mutableVector.content;
                int size$iv$iv = mutableVector.getSize();
                for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                    ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv$iv[i$iv$iv];
                    hasValues = scopeMap.recordInvalidation(setRemoveChanges) || hasValues;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    private final void sendNotifications() {
        this.onChangedExecutor.invoke(new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SnapshotStateObserver.sendNotifications$lambda$0(this.f$0);
            }
        });
    }

    static final Unit sendNotifications$lambda$0(SnapshotStateObserver this$0) {
        do {
            Object lock$iv = this$0.observedScopeMapsLock;
            synchronized (lock$iv) {
                if (!this$0.sendingNotifications) {
                    this$0.sendingNotifications = true;
                    try {
                        MutableVector<ObservedScopeMap> mutableVector = this$0.observedScopeMaps;
                        Object[] content$iv = mutableVector.content;
                        int size$iv = mutableVector.getSize();
                        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                            ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv[i$iv];
                            scopeMap.notifyInvalidatedScopes();
                        }
                        this$0.sendingNotifications = false;
                    } catch (Throwable th) {
                        this$0.sendingNotifications = false;
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        } while (this$0.drainChanges());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void addChanges(Set<? extends Object> set) {
        Object old;
        Collection collectionPlus;
        do {
            old = this.pendingChanges.get();
            if (old == null) {
                collectionPlus = set;
            } else if (old instanceof Set) {
                collectionPlus = CollectionsKt.listOf((Object[]) new Set[]{old, set});
            } else {
                if (!(old instanceof List)) {
                    report();
                    throw new KotlinNothingValueException();
                }
                collectionPlus = CollectionsKt.plus((Collection) old, (Iterable) CollectionsKt.listOf(set));
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, old, collectionPlus));
    }

    private final Set<Object> removeChanges() {
        Object old;
        Set<Object> set;
        Object obj;
        do {
            old = this.pendingChanges.get();
            Object objSubList = null;
            if (old == null) {
                return null;
            }
            if (old instanceof Set) {
                set = (Set) old;
                obj = null;
            } else if (old instanceof List) {
                set = (Set) ((List) old).get(0);
                if (((List) old).size() == 2) {
                    objSubList = ((List) old).get(1);
                } else if (((List) old).size() > 2) {
                    objSubList = ((List) old).subList(1, ((List) old).size());
                }
                obj = objSubList;
            } else {
                report();
                throw new KotlinNothingValueException();
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingChanges, old, obj));
        return set;
    }

    private final Void report() {
        ComposerKt.composeRuntimeError("Unexpected notification");
        throw new KotlinNothingValueException();
    }

    static final Unit readObserver$lambda$0(SnapshotStateObserver this$0, Object state) {
        if (!this$0.isPaused) {
            Object lock$iv = this$0.observedScopeMapsLock;
            synchronized (lock$iv) {
                ObservedScopeMap observedScopeMap = this$0.currentMap;
                Intrinsics.checkNotNull(observedScopeMap);
                observedScopeMap.recordRead(state);
                Unit unit = Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    private final void forEachScopeMap(Function1<? super ObservedScopeMap, Unit> block) {
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            Object[] content$iv = mutableVector.content;
            int size$iv = mutableVector.getSize();
            for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                block.invoke(content$iv[i$iv]);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void removeScopeMapIf(Function1<? super ObservedScopeMap, Boolean> block) {
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int gap$iv = 0;
            int size$iv = mutableVector.getSize();
            for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                if (block.invoke(mutableVector.content[i$iv]).booleanValue()) {
                    gap$iv++;
                } else if (gap$iv > 0) {
                    mutableVector.content[i$iv - gap$iv] = mutableVector.content[i$iv];
                }
            }
            ArraysKt.fill(mutableVector.content, (Object) null, size$iv - gap$iv, size$iv);
            mutableVector.setSize(size$iv - gap$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> void observeReads(T scope, Function1<? super T, Unit> onValueChangedForScope, Function0<Unit> block) {
        ObservedScopeMap scopeMap;
        Function1<Object, Unit> function1;
        Function1<Object, Unit> function2;
        TransparentObserverMutableSnapshot transparentObserverMutableSnapshot;
        Object lock$iv = this.observedScopeMapsLock;
        synchronized (lock$iv) {
            scopeMap = ensureMap(onValueChangedForScope);
        }
        boolean oldPaused = this.isPaused;
        ObservedScopeMap oldMap = this.currentMap;
        long oldThreadId = this.currentMapThreadId;
        if (oldThreadId != -1) {
            boolean value$iv = oldThreadId == Thread_jvmKt.currentThreadId();
            if (!value$iv) {
                PreconditionsKt.throwIllegalArgumentException("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + oldThreadId + "), currentThread={id=" + Thread_jvmKt.currentThreadId() + ", name=" + Thread_jvmKt.currentThreadName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
            }
        }
        try {
            this.isPaused = false;
            this.currentMap = scopeMap;
            this.currentMapThreadId = Thread_jvmKt.currentThreadId();
            Function1<Object, Unit> function3 = this.readObserver;
            Object previousScope$iv = scopeMap.currentScope;
            MutableObjectIntMap previousReads$iv = scopeMap.currentScopeReads;
            int previousToken$iv = scopeMap.currentToken;
            scopeMap.currentScope = scope;
            scopeMap.currentScopeReads = (MutableObjectIntMap) scopeMap.scopeToValues.get(scope);
            if (scopeMap.currentToken == -1) {
                try {
                    scopeMap.currentToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
                } catch (Throwable th) {
                    th = th;
                    this.currentMap = oldMap;
                    this.isPaused = oldPaused;
                    this.currentMapThreadId = oldThreadId;
                    throw th;
                }
            }
            DerivedStateObserver observer$iv$iv = scopeMap.getDerivedStateObserver();
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                try {
                    mutableVectorDerivedStateObservers.add(observer$iv$iv);
                    Snapshot.Companion companion = Snapshot.INSTANCE;
                    if (function3 == null) {
                        try {
                            block.invoke();
                        } catch (Throwable th2) {
                            th = th2;
                            mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                            throw th;
                        }
                    } else {
                        Snapshot previous$iv$iv = (Snapshot) SnapshotKt.threadSnapshot.get();
                        try {
                            try {
                                if (previous$iv$iv instanceof TransparentObserverMutableSnapshot) {
                                    try {
                                        TransparentObserverMutableSnapshot $this$canBeReused$iv$iv$iv = (TransparentObserverMutableSnapshot) previous$iv$iv;
                                        if ($this$canBeReused$iv$iv$iv.getThreadId() == Thread_jvmKt.currentThreadId()) {
                                            Function1<Object, Unit> readObserver = ((TransparentObserverMutableSnapshot) previous$iv$iv).getReadObserver();
                                            Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) previous$iv$iv).getWriteObserver$runtime();
                                            try {
                                                function1 = readObserver;
                                                try {
                                                    ((TransparentObserverMutableSnapshot) previous$iv$iv).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(function3, function1, false, 4, null));
                                                    function2 = writeObserver$runtime;
                                                    try {
                                                        ((TransparentObserverMutableSnapshot) previous$iv$iv).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(null, function2));
                                                        block.invoke();
                                                        ((TransparentObserverMutableSnapshot) previous$iv$iv).setReadObserver$runtime(function1);
                                                        ((TransparentObserverMutableSnapshot) previous$iv$iv).setWriteObserver$runtime(function2);
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        ((TransparentObserverMutableSnapshot) previous$iv$iv).setReadObserver$runtime(function1);
                                                        ((TransparentObserverMutableSnapshot) previous$iv$iv).setWriteObserver$runtime(function2);
                                                        throw th;
                                                    }
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    function2 = writeObserver$runtime;
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                function1 = readObserver;
                                                function2 = writeObserver$runtime;
                                            }
                                        }
                                    } catch (Throwable th6) {
                                        th = th6;
                                        mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                                        throw th;
                                    }
                                }
                                if (previous$iv$iv == null || (previous$iv$iv instanceof MutableSnapshot)) {
                                    try {
                                        transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(previous$iv$iv instanceof MutableSnapshot ? (MutableSnapshot) previous$iv$iv : null, function3, null, true, false);
                                    } catch (Throwable th7) {
                                        th = th7;
                                        mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                                        throw th;
                                    }
                                } else {
                                    transparentObserverMutableSnapshot = previous$iv$iv.takeNestedSnapshot(function3);
                                }
                                Snapshot snapshot$iv$iv = transparentObserverMutableSnapshot;
                                try {
                                    Snapshot previous$iv$iv$iv = snapshot$iv$iv.makeCurrent();
                                    try {
                                        block.invoke();
                                        snapshot$iv$iv.restoreCurrent(previous$iv$iv$iv);
                                        snapshot$iv$iv.dispose();
                                    } catch (Throwable th8) {
                                        try {
                                            snapshot$iv$iv.restoreCurrent(previous$iv$iv$iv);
                                            throw th8;
                                        } catch (Throwable th9) {
                                            th = th9;
                                            try {
                                                snapshot$iv$iv.dispose();
                                                throw th;
                                            } catch (Throwable th10) {
                                                th = th10;
                                                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                                                throw th;
                                            }
                                        }
                                    }
                                } catch (Throwable th11) {
                                    th = th11;
                                }
                            } catch (Throwable th12) {
                                th = th12;
                            }
                        } catch (Throwable th13) {
                            th = th13;
                        }
                    }
                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                    Object obj = scopeMap.currentScope;
                    Intrinsics.checkNotNull(obj);
                    scopeMap.clearObsoleteStateReads(obj);
                    scopeMap.currentScope = previousScope$iv;
                    scopeMap.currentScopeReads = previousReads$iv;
                    scopeMap.currentToken = previousToken$iv;
                    this.currentMap = oldMap;
                    this.isPaused = oldPaused;
                    this.currentMapThreadId = oldThreadId;
                } catch (Throwable th14) {
                    th = th14;
                }
            } catch (Throwable th15) {
                th = th15;
                this.currentMap = oldMap;
                this.isPaused = oldPaused;
                this.currentMapThreadId = oldThreadId;
                throw th;
            }
        } catch (Throwable th16) {
            th = th16;
        }
    }

    @Deprecated(message = "Replace with Snapshot.withoutReadObservation()", replaceWith = @ReplaceWith(expression = "Snapshot.withoutReadObservation(block)", imports = {"androidx.compose.runtime.snapshots.Snapshot"}))
    public final void withNoObservations(Function0<Unit> block) {
        boolean oldPaused = this.isPaused;
        this.isPaused = true;
        try {
            block.invoke();
        } finally {
            this.isPaused = oldPaused;
        }
    }

    public final void clear(Object scope) {
        Object lock$iv$iv = this.observedScopeMapsLock;
        synchronized (lock$iv$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int gap$iv$iv = 0;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap it = mutableVector.content[i$iv$iv];
                it.clearScopeObservations(scope);
                if (!it.hasScopeObservations()) {
                    gap$iv$iv++;
                } else if (gap$iv$iv > 0) {
                    mutableVector.content[i$iv$iv - gap$iv$iv] = mutableVector.content[i$iv$iv];
                }
            }
            ArraysKt.fill(mutableVector.content, (Object) null, size$iv$iv - gap$iv$iv, size$iv$iv);
            mutableVector.setSize(size$iv$iv - gap$iv$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clearIf(Function1<Object, Boolean> predicate) {
        Object lock$iv$iv = this.observedScopeMapsLock;
        synchronized (lock$iv$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            int gap$iv$iv = 0;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap scopeMap = mutableVector.content[i$iv$iv];
                scopeMap.removeScopeIf(predicate);
                if (!scopeMap.hasScopeObservations()) {
                    gap$iv$iv++;
                } else if (gap$iv$iv > 0) {
                    mutableVector.content[i$iv$iv - gap$iv$iv] = mutableVector.content[i$iv$iv];
                }
            }
            ArraysKt.fill(mutableVector.content, (Object) null, size$iv$iv - gap$iv$iv, size$iv$iv);
            mutableVector.setSize(size$iv$iv - gap$iv$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void start() {
        this.applyUnsubscribe = Snapshot.INSTANCE.registerApplyObserver(this.applyObserver);
    }

    public final void stop() {
        ObserverHandle observerHandle = this.applyUnsubscribe;
        if (observerHandle != null) {
            observerHandle.dispose();
        }
    }

    public final void notifyChanges(Set<? extends Object> changes, Snapshot snapshot) {
        this.applyObserver.invoke(changes, snapshot);
    }

    public final void clear() {
        Object lock$iv$iv = this.observedScopeMapsLock;
        synchronized (lock$iv$iv) {
            MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
            Object[] content$iv$iv = mutableVector.content;
            int size$iv$iv = mutableVector.getSize();
            for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
                ObservedScopeMap scopeMap = (ObservedScopeMap) content$iv$iv[i$iv$iv];
                scopeMap.clear();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final <T> ObservedScopeMap ensureMap(Function1<? super T, Unit> onChanged) {
        ObservedScopeMap item$iv;
        MutableVector<ObservedScopeMap> mutableVector = this.observedScopeMaps;
        ObservedScopeMap[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        int i$iv = 0;
        while (true) {
            if (i$iv < size$iv) {
                item$iv = content$iv[i$iv];
                if (item$iv.getOnChanged() == onChanged) {
                    break;
                }
                i$iv++;
            } else {
                item$iv = null;
                break;
            }
        }
        ObservedScopeMap scopeMap = item$iv;
        if (scopeMap == null) {
            Intrinsics.checkNotNull(onChanged, "null cannot be cast to non-null type kotlin.Function1<kotlin.Any, kotlin.Unit>");
            ObservedScopeMap map = new ObservedScopeMap((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(onChanged, 1));
            this.observedScopeMaps.add(map);
            return map;
        }
        return scopeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SnapshotStateObserver.kt */
    @Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0001J.\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0002J7\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00012\u0014\b\b\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\b\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040.H\u0086\bJ\u0010\u0010/\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0001H\u0002J\u000e\u00100\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0001J)\u00101\u001a\u00020\u00042!\u00102\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u001d0\u0003J\u0006\u00105\u001a\u00020\u001dJ\u0018\u00106\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u0001H\u0002J\u0006\u00107\u001a\u00020\u0004J\u0014\u00108\u001a\u00020\u001d2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00010:J\u0012\u0010;\u001a\u00020\u00042\n\u0010<\u001a\u0006\u0012\u0002\b\u00030\u0017J\u0006\u0010=\u001a\u00020\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R6\u0010$\u001a*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010%j\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u0001`&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotStateObserver$ObservedScopeMap;", "", "onChanged", "Lkotlin/Function1;", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "currentScope", "currentScopeReads", "Landroidx/collection/MutableObjectIntMap;", "currentToken", "", "valueToScopes", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/collection/MutableScatterMap;", "scopeToValues", "Landroidx/collection/MutableScatterMap;", "invalidated", "Landroidx/collection/MutableScatterSet;", "statesToReread", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/DerivedState;", "derivedStateObserver", "Landroidx/compose/runtime/DerivedStateObserver;", "getDerivedStateObserver", "()Landroidx/compose/runtime/DerivedStateObserver;", "readingDerivedStates", "", "getReadingDerivedStates", "()Z", "setReadingDerivedStates", "(Z)V", "deriveStateScopeCount", "dependencyToDerivedStates", "recordedDerivedStateValues", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recordRead", "value", "recordedValues", "observe", "scope", "readObserver", "block", "Lkotlin/Function0;", "clearObsoleteStateReads", "clearScopeObservations", "removeScopeIf", "predicate", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "hasScopeObservations", "removeObservation", "clear", "recordInvalidation", "changes", "", "rereadDerivedState", "derivedState", "notifyInvalidatedScopes", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class ObservedScopeMap {
        private Object currentScope;
        private MutableObjectIntMap<Object> currentScopeReads;
        private int deriveStateScopeCount;
        private final MutableScatterSet<Object> invalidated;
        private final Function1<Object, Unit> onChanged;
        private boolean readingDerivedStates;
        private final MutableScatterMap<Object, MutableObjectIntMap<Object>> scopeToValues;
        private int currentToken = -1;
        private final MutableScatterMap<Object, Object> valueToScopes = ScopeMap.m4469constructorimpl$default(null, 1, null);
        private final MutableVector<DerivedState<?>> statesToReread = new MutableVector<>(new DerivedState[16], 0);
        private final DerivedStateObserver derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount++;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                this.this$0.deriveStateScopeCount--;
            }
        };
        private final MutableScatterMap<Object, Object> dependencyToDerivedStates = ScopeMap.m4469constructorimpl$default(null, 1, null);
        private final HashMap<DerivedState<?>, Object> recordedDerivedStateValues = new HashMap<>();

        public ObservedScopeMap(Function1<Object, Unit> function1) {
            this.onChanged = function1;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            this.scopeToValues = new MutableScatterMap<>(i2, i, defaultConstructorMarker);
            this.invalidated = new MutableScatterSet<>(i2, i, defaultConstructorMarker);
        }

        public final Function1<Object, Unit> getOnChanged() {
            return this.onChanged;
        }

        public final DerivedStateObserver getDerivedStateObserver() {
            return this.derivedStateObserver;
        }

        public final boolean getReadingDerivedStates() {
            return this.readingDerivedStates;
        }

        public final void setReadingDerivedStates(boolean z) {
            this.readingDerivedStates = z;
        }

        public final void recordRead(Object value) {
            Object scope = this.currentScope;
            Intrinsics.checkNotNull(scope);
            int i = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
                this.currentScopeReads = mutableObjectIntMap;
                this.scopeToValues.set(scope, mutableObjectIntMap);
                Unit unit = Unit.INSTANCE;
            }
            recordRead(value, i, scope, mutableObjectIntMap);
        }

        /* JADX WARN: Code duplicated, block: B:27:0x00be A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:28:0x00c0 A[LOOP:0: B:10:0x0043->B:28:0x00c0, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:42:0x00dc A[EDGE_INSN: B:42:0x00dc->B:33:0x00dc BREAK  A[LOOP:0: B:10:0x0043->B:28:0x00c0], SYNTHETIC] */
        private final void recordRead(Object value, int currentToken, Object currentScope, MutableObjectIntMap<Object> recordedValues) {
            int i;
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            int previousToken = recordedValues.put(value, currentToken, -1);
            if (!(value instanceof DerivedState) || previousToken == currentToken) {
                i = 2;
            } else {
                DerivedState.Record record = ((DerivedState) value).getCurrentRecord();
                this.recordedDerivedStateValues.put(value, record.getCurrentValue());
                ObjectIntMap<StateObject> dependencies = record.getDependencies();
                MutableScatterMap<Object, Object> mutableScatterMap = this.dependencyToDerivedStates;
                ScopeMap.m4478removeScopeimpl(mutableScatterMap, value);
                ObjectIntMap<StateObject> objectIntMap = dependencies;
                int $i$f$forEachKey = 0;
                Object[] k$iv = objectIntMap.keys;
                ObjectIntMap<StateObject> objectIntMap2 = objectIntMap;
                long[] m$iv$iv = objectIntMap2.metadata;
                i = 2;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        long slot$iv$iv = m$iv$iv[i$iv$iv];
                        ObjectIntMap<StateObject> objectIntMap3 = objectIntMap;
                        int $i$f$forEachKey2 = $i$f$forEachKey;
                        Object[] k$iv2 = k$iv;
                        ObjectIntMap<StateObject> objectIntMap4 = objectIntMap2;
                        if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                            if (i$iv$iv != lastIndex$iv$iv) {
                                break;
                                break;
                            }
                            i$iv$iv++;
                            k$iv = k$iv2;
                            objectIntMap2 = objectIntMap4;
                            objectIntMap = objectIntMap3;
                            $i$f$forEachKey = $i$f$forEachKey2;
                        } else {
                            int i2 = 8;
                            int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                            int j$iv$iv = 0;
                            while (j$iv$iv < bitCount$iv$iv) {
                                long value$iv$iv$iv = slot$iv$iv & 255;
                                if (value$iv$iv$iv < 128) {
                                    int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                    StateObject dependency = (StateObject) k$iv2[index$iv$iv];
                                    if (dependency instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                        ((StateObjectImpl) dependency).m4559recordReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(2));
                                    }
                                    ScopeMap.m4463addimpl(mutableScatterMap, dependency, value);
                                }
                                slot$iv$iv >>= i2;
                                j$iv$iv++;
                                i2 = i2;
                            }
                            if (bitCount$iv$iv != i2) {
                                break;
                            }
                            if (i$iv$iv != lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            k$iv = k$iv2;
                            objectIntMap2 = objectIntMap4;
                            objectIntMap = objectIntMap3;
                            $i$f$forEachKey = $i$f$forEachKey2;
                        }
                    }
                }
            }
            if (previousToken == -1) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m4559recordReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(i));
                }
                ScopeMap.m4463addimpl(this.valueToScopes, value, currentScope);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void observe(Object scope, Function1<Object, Unit> readObserver, Function0<Unit> block) throws Throwable {
            Snapshot transparentObserverMutableSnapshot;
            Function1<Object, Unit> function1;
            Function1<Object, Unit> function2;
            Object previousScope = this.currentScope;
            MutableObjectIntMap previousReads = this.currentScopeReads;
            int previousToken = this.currentToken;
            this.currentScope = scope;
            this.currentScopeReads = (MutableObjectIntMap) this.scopeToValues.get(scope);
            if (this.currentToken == -1) {
                this.currentToken = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            }
            DerivedStateObserver observer$iv = getDerivedStateObserver();
            MutableVector<DerivedStateObserver> mutableVectorDerivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                mutableVectorDerivedStateObservers.add(observer$iv);
                Snapshot.Companion companion = Snapshot.INSTANCE;
                if (readObserver == null) {
                    try {
                        block.invoke();
                    } catch (Throwable th) {
                        th = th;
                        mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                        throw th;
                    }
                } else {
                    Snapshot previous$iv = (Snapshot) SnapshotKt.threadSnapshot.get();
                    try {
                        if (previous$iv instanceof TransparentObserverMutableSnapshot) {
                            TransparentObserverMutableSnapshot $this$canBeReused$iv$iv = (TransparentObserverMutableSnapshot) previous$iv;
                            if ($this$canBeReused$iv$iv.getThreadId() == Thread_jvmKt.currentThreadId()) {
                                try {
                                    Function1<Object, Unit> readObserver2 = ((TransparentObserverMutableSnapshot) previous$iv).getReadObserver();
                                    Function1<Object, Unit> writeObserver$runtime = ((TransparentObserverMutableSnapshot) previous$iv).getWriteObserver$runtime();
                                    try {
                                        function1 = readObserver2;
                                        try {
                                            ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(SnapshotKt.mergedReadObserver$default(readObserver, function1, false, 4, null));
                                            function2 = writeObserver$runtime;
                                            try {
                                                ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(SnapshotKt.mergedWriteObserver(null, function2));
                                                block.invoke();
                                                ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(function1);
                                                ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(function2);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                ((TransparentObserverMutableSnapshot) previous$iv).setReadObserver$runtime(function1);
                                                ((TransparentObserverMutableSnapshot) previous$iv).setWriteObserver$runtime(function2);
                                                throw th;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            function2 = writeObserver$runtime;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        function1 = readObserver2;
                                        function2 = writeObserver$runtime;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                                    throw th;
                                }
                            }
                        }
                        if (previous$iv == null || (previous$iv instanceof MutableSnapshot)) {
                            transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(previous$iv instanceof MutableSnapshot ? (MutableSnapshot) previous$iv : null, readObserver, null, true, false);
                        } else {
                            transparentObserverMutableSnapshot = previous$iv.takeNestedSnapshot(readObserver);
                        }
                        Snapshot snapshot$iv = transparentObserverMutableSnapshot;
                        try {
                            Snapshot previous$iv$iv = snapshot$iv.makeCurrent();
                            try {
                                block.invoke();
                                snapshot$iv.restoreCurrent(previous$iv$iv);
                                snapshot$iv.dispose();
                            } catch (Throwable th6) {
                                snapshot$iv.restoreCurrent(previous$iv$iv);
                                throw th6;
                            }
                        } catch (Throwable th7) {
                            snapshot$iv.dispose();
                            throw th7;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                        throw th;
                    }
                }
                mutableVectorDerivedStateObservers.removeAt(mutableVectorDerivedStateObservers.getSize() - 1);
                Object obj = this.currentScope;
                Intrinsics.checkNotNull(obj);
                clearObsoleteStateReads(obj);
                this.currentScope = previousScope;
                this.currentScopeReads = previousReads;
                this.currentToken = previousToken;
            } catch (Throwable th9) {
                th = th9;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearObsoleteStateReads(Object scope) {
            int currentToken;
            int $i$f$removeIf;
            int $i$f$removeIf2;
            int i;
            int currentToken2 = this.currentToken;
            MutableObjectIntMap<Object> mutableObjectIntMap = this.currentScopeReads;
            if (mutableObjectIntMap == null) {
                return;
            }
            int $i$f$removeIf3 = 0;
            MutableObjectIntMap<Object> this_$iv$iv = mutableObjectIntMap;
            long[] m$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    currentToken = currentToken2;
                    $i$f$removeIf = $i$f$removeIf3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            currentToken2 = currentToken2;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            Object value = mutableObjectIntMap.keys[index$iv$iv];
                            $i$f$removeIf2 = $i$f$removeIf3;
                            int token = mutableObjectIntMap.values[index$iv$iv];
                            boolean z = token != currentToken2;
                            boolean willRemove = z;
                            if (willRemove) {
                                removeObservation(scope, value);
                            }
                            if (z) {
                                mutableObjectIntMap.removeValueAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        currentToken2 = currentToken2;
                    }
                    currentToken = currentToken2;
                    $i$f$removeIf = $i$f$removeIf3;
                    if (bitCount$iv$iv != i2) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                $i$f$removeIf3 = $i$f$removeIf;
                currentToken2 = currentToken;
            }
        }

        public final void clearScopeObservations(Object scope) {
            MutableObjectIntMap recordedValues = this.scopeToValues.remove(scope);
            if (recordedValues == null) {
                return;
            }
            MutableObjectIntMap this_$iv = recordedValues;
            Object[] k$iv = this_$iv.keys;
            int[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                MutableObjectIntMap recordedValues2 = recordedValues;
                ObjectIntMap this_$iv2 = this_$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            Object value = k$iv[index$iv$iv];
                            int i2 = v$iv[index$iv$iv];
                            removeObservation(scope, value);
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i = i;
                    }
                    if (bitCount$iv$iv != i) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                recordedValues = recordedValues2;
                this_$iv = this_$iv2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:31:0x00e5 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:32:0x00e7 A[LOOP:2: B:17:0x0094->B:32:0x00e7, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:61:0x0104 A[EDGE_INSN: B:61:0x0104->B:37:0x0104 BREAK  A[LOOP:2: B:17:0x0094->B:32:0x00e7], SYNTHETIC] */
        public final void removeScopeIf(Function1<Object, Boolean> predicate) {
            int $i$f$removeIf;
            ScatterMap this_$iv$iv;
            int $i$f$forEachIndexed;
            long[] m$iv$iv;
            int $i$f$removeIf2;
            ScatterMap this_$iv$iv2;
            int $i$f$forEachIndexed2;
            long[] m$iv$iv2;
            long $this$maskEmptyOrDeleted$iv$iv$iv;
            char c;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            int $i$f$removeIf3 = 0;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> this_$iv$iv3 = mutableScatterMap;
            int $i$f$forEachIndexed3 = 0;
            long[] m$iv$iv3 = this_$iv$iv3.metadata;
            int lastIndex$iv$iv = m$iv$iv3.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv3[i$iv$iv];
                char c2 = 7;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        if (!(value$iv$iv$iv < 128)) {
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv2 = m$iv$iv3;
                            $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                            c = c2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            Object scope = mutableScatterMap.keys[index$iv$iv];
                            ObjectIntMap valueSet = (MutableObjectIntMap) mutableScatterMap.values[index$iv$iv];
                            c = c2;
                            Boolean boolInvoke = predicate.invoke(scope);
                            boolean willRemove = boolInvoke.booleanValue();
                            if (!willRemove) {
                                $i$f$removeIf2 = $i$f$removeIf3;
                                this_$iv$iv2 = this_$iv$iv3;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv2 = m$iv$iv3;
                                $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                            } else {
                                int i2 = i;
                                ObjectIntMap this_$iv = valueSet;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                Object[] k$iv = this_$iv.keys;
                                int[] v$iv = this_$iv.values;
                                this_$iv$iv2 = this_$iv$iv3;
                                long[] m$iv$iv4 = this_$iv.metadata;
                                int lastIndex$iv$iv2 = m$iv$iv4.length - 2;
                                int i$iv$iv2 = 0;
                                if (0 <= lastIndex$iv$iv2) {
                                    while (true) {
                                        long slot$iv$iv2 = m$iv$iv4[i$iv$iv2];
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        m$iv$iv2 = m$iv$iv3;
                                        $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                                        if ((((~slot$iv$iv2) << c) & slot$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                            if (i$iv$iv2 != lastIndex$iv$iv2) {
                                                break;
                                                break;
                                            }
                                            i$iv$iv2++;
                                            slot$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv;
                                            $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                            m$iv$iv3 = m$iv$iv2;
                                            i2 = 8;
                                        } else {
                                            int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                                            for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                                                long value$iv$iv$iv2 = slot$iv$iv2 & 255;
                                                if (value$iv$iv$iv2 < 128) {
                                                    int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv2;
                                                    Object value = k$iv[index$iv$iv2];
                                                    int i3 = v$iv[index$iv$iv2];
                                                    removeObservation(scope, value);
                                                }
                                                slot$iv$iv2 >>= i2;
                                            }
                                            int j$iv$iv3 = i2;
                                            if (bitCount$iv$iv2 != j$iv$iv3) {
                                                break;
                                            }
                                            if (i$iv$iv2 != lastIndex$iv$iv2) {
                                                break;
                                            }
                                            i$iv$iv2++;
                                            slot$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv;
                                            $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                            m$iv$iv3 = m$iv$iv2;
                                            i2 = 8;
                                        }
                                    }
                                } else {
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv2 = m$iv$iv3;
                                    $this$maskEmptyOrDeleted$iv$iv$iv = slot$iv$iv;
                                }
                            }
                            if (boolInvoke.booleanValue()) {
                                mutableScatterMap.removeValueAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv >> 8;
                        j$iv$iv++;
                        i = 8;
                        c2 = c;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        this_$iv$iv3 = this_$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        m$iv$iv3 = m$iv$iv2;
                    }
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv = m$iv$iv3;
                    if (bitCount$iv$iv != i) {
                        return;
                    }
                } else {
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv = m$iv$iv3;
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                }
                i$iv$iv++;
                $i$f$removeIf3 = $i$f$removeIf;
                this_$iv$iv3 = this_$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv3 = m$iv$iv;
            }
        }

        public final boolean hasScopeObservations() {
            return this.scopeToValues.isNotEmpty();
        }

        private final void removeObservation(Object scope, Object value) {
            ScopeMap.m4476removeimpl(this.valueToScopes, value, scope);
            if ((value instanceof DerivedState) && !ScopeMap.m4470containsimpl(this.valueToScopes, value)) {
                ScopeMap.m4478removeScopeimpl(this.dependencyToDerivedStates, value);
                this.recordedDerivedStateValues.remove(value);
            }
        }

        public final void clear() {
            ScopeMap.m4467clearimpl(this.valueToScopes);
            this.scopeToValues.clear();
            ScopeMap.m4467clearimpl(this.dependencyToDerivedStates);
            this.recordedDerivedStateValues.clear();
        }

        /* JADX WARN: Code duplicated, block: B:210:0x05da  */
        /* JADX WARN: Code duplicated, block: B:366:0x098d  */
        /* JADX WARN: Code duplicated, block: B:369:0x09ab  */
        /* JADX WARN: Code duplicated, block: B:371:0x09af  */
        /* JADX WARN: Code duplicated, block: B:373:0x09c3 A[LOOP:12: B:373:0x09c3->B:390:0x0a30, LOOP_START, PHI: r0 r2 r3 r5 r8 r15
  0x09c3: PHI (r0v6 'key$iv' java.lang.Object) = (r0v4 'key$iv' java.lang.Object), (r0v7 'key$iv' java.lang.Object) binds: [B:372:0x09c1, B:390:0x0a30] A[DONT_GENERATE, DONT_INLINE]
  0x09c3: PHI (r2v4 androidx.collection.MutableScatterMap) = (r2v2 androidx.collection.MutableScatterMap), (r2v10 androidx.collection.MutableScatterMap) binds: [B:372:0x09c1, B:390:0x0a30] A[DONT_GENERATE, DONT_INLINE]
  0x09c3: PHI (r3v7 int) = (r3v3 int), (r3v10 int) binds: [B:372:0x09c1, B:390:0x0a30] A[DONT_GENERATE, DONT_INLINE]
  0x09c3: PHI (r5v2 androidx.collection.MutableScatterSet<java.lang.Object>) = 
  (r5v1 androidx.collection.MutableScatterSet<java.lang.Object>)
  (r5v3 androidx.collection.MutableScatterSet<java.lang.Object>)
 binds: [B:372:0x09c1, B:390:0x0a30] A[DONT_GENERATE, DONT_INLINE]
  0x09c3: PHI (r8v5 'hasValues' boolean) = (r8v3 'hasValues' boolean), (r8v6 'hasValues' boolean) binds: [B:372:0x09c1, B:390:0x0a30] A[DONT_GENERATE, DONT_INLINE]
  0x09c3: PHI (r15v1 'i$iv$iv$iv' int) = (r15v0 'i$iv$iv$iv' int), (r15v2 'i$iv$iv$iv' int) binds: [B:372:0x09c1, B:390:0x0a30] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:375:0x09db  */
        /* JADX WARN: Code duplicated, block: B:377:0x09e7  */
        /* JADX WARN: Code duplicated, block: B:379:0x09ef  */
        /* JADX WARN: Code duplicated, block: B:380:0x09f1  */
        /* JADX WARN: Code duplicated, block: B:382:0x09f4  */
        /* JADX WARN: Code duplicated, block: B:383:0x0a0b  */
        /* JADX WARN: Code duplicated, block: B:388:0x0a28  */
        /* JADX WARN: Code duplicated, block: B:390:0x0a30 A[LOOP:12: B:373:0x09c3->B:390:0x0a30, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:391:0x0a3b  */
        /* JADX WARN: Code duplicated, block: B:394:0x0a47  */
        /* JADX WARN: Code duplicated, block: B:395:0x0a58  */
        /* JADX WARN: Code duplicated, block: B:463:0x021b A[EDGE_INSN: B:463:0x021b->B:82:0x021b BREAK  A[LOOP:4: B:62:0x01ad->B:79:0x0205], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:482:0x0a63 A[EDGE_INSN: B:482:0x0a63->B:397:0x0a63 BREAK  A[LOOP:12: B:373:0x09c3->B:390:0x0a30], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:484:0x0a63 A[EDGE_INSN: B:484:0x0a63->B:397:0x0a63 BREAK  A[LOOP:12: B:373:0x09c3->B:390:0x0a30], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:78:0x0203 A[DONT_INVERT, PHI: r40
  0x0203: PHI (r40v37 'hasValues' boolean) = (r40v36 'hasValues' boolean), (r40v38 'hasValues' boolean) binds: [B:64:0x01c5, B:77:0x0201] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:79:0x0205 A[Catch: all -> 0x03fe, LOOP:4: B:62:0x01ad->B:79:0x0205, LOOP_END, TryCatch #17 {all -> 0x03fe, blocks: (B:73:0x01e4, B:75:0x01f8, B:85:0x023e, B:93:0x027c, B:79:0x0205, B:83:0x021d, B:86:0x0242, B:99:0x02a7, B:103:0x02d5, B:105:0x02f7, B:106:0x02fb, B:108:0x030c, B:110:0x0316, B:112:0x031a, B:114:0x033c, B:116:0x0356, B:118:0x0362, B:123:0x036f, B:125:0x0389, B:131:0x039d, B:135:0x03bb, B:144:0x03f1), top: B:445:0x01e4 }] */
        public final boolean recordInvalidation(Set<? extends Object> changes) throws Throwable {
            boolean hasValues;
            MutableScatterMap<Object, Object> mutableScatterMap;
            Iterable $this$forEach$iv$iv;
            int $i$f$forEach;
            Iterator it;
            String str;
            Object value;
            Object key$iv;
            MutableScatterMap mutableScatterMap2;
            int i;
            Object value$iv;
            MutableScatterSet<Object> mutableScatterSet;
            Object[] elements$iv$iv;
            long[] m$iv$iv$iv;
            int lastIndex$iv$iv$iv;
            int i$iv$iv$iv;
            long slot$iv$iv$iv;
            MutableScatterSet<Object> mutableScatterSet2;
            MutableScatterMap mutableScatterMap3;
            int i2;
            Object key$iv2;
            int bitCount$iv$iv$iv;
            int j$iv$iv$iv;
            long value$iv$iv$iv$iv;
            boolean z;
            MutableScatterSet<Object> mutableScatterSet3;
            boolean z2;
            int i3;
            Object key$iv3;
            Iterable $this$forEach$iv$iv2;
            int j$iv$iv$iv2;
            String str2;
            int i4;
            Object key$iv4;
            Object previousValue;
            MutableScatterMap valueToScopes;
            ScatterSet this_$iv$iv;
            long[] m$iv$iv$iv2;
            int lastIndex$iv$iv$iv2;
            int i$iv$iv$iv2;
            ScatterSet this_$iv$iv$iv;
            MutableScatterMap valueToScopes2;
            ScatterSet this_$iv$iv2;
            long[] m$iv$iv$iv3;
            int lastIndex$iv$iv$iv3;
            int i$iv$iv$iv3;
            int j$iv$iv$iv3;
            ScatterSet this_$iv$iv$iv2;
            boolean hasValues2;
            Object value$iv2;
            boolean hasValues3;
            boolean z3;
            SnapshotMutationPolicy<?> snapshotMutationPolicy;
            int lastIndex$iv$iv$iv4;
            int i$iv$iv$iv4;
            ScatterSet this_$iv$iv$iv3;
            boolean hasValues4 = false;
            MutableScatterMap<Object, Object> mutableScatterMap4 = this.dependencyToDerivedStates;
            HashMap<DerivedState<?>, Object> map = this.recordedDerivedStateValues;
            MutableScatterMap valueToScopes3 = this.valueToScopes;
            MutableScatterSet<Object> mutableScatterSet4 = this.invalidated;
            Set $this$fastForEach$iv = changes;
            int $i$f$fastForEach = 0;
            String str3 = "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>";
            if ($this$fastForEach$iv instanceof ScatterSetWrapper) {
                ScatterSet this_$iv$iv3 = ((ScatterSetWrapper) $this$fastForEach$iv).getSet$runtime();
                int $i$f$forEach2 = 0;
                Object[] elements$iv$iv2 = this_$iv$iv3.elements;
                ScatterSet this_$iv$iv$iv4 = this_$iv$iv3;
                long[] m$iv$iv$iv4 = this_$iv$iv$iv4.metadata;
                int i5 = 8;
                int lastIndex$iv$iv$iv5 = m$iv$iv$iv4.length - 2;
                int i$iv$iv$iv5 = 0;
                if (0 <= lastIndex$iv$iv$iv5) {
                    while (true) {
                        long slot$iv$iv$iv2 = m$iv$iv$iv4[i$iv$iv$iv5];
                        Set $this$fastForEach$iv2 = $this$fastForEach$iv;
                        int $i$f$fastForEach2 = $i$f$fastForEach;
                        int $i$f$forEach3 = $i$f$forEach2;
                        Object[] elements$iv$iv3 = elements$iv$iv2;
                        if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv5 - lastIndex$iv$iv$iv5)) >>> 31);
                            int j$iv$iv$iv4 = 0;
                            boolean hasValues5 = hasValues4;
                            while (j$iv$iv$iv4 < bitCount$iv$iv$iv2) {
                                long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                int $i$f$isFull = value$iv$iv$iv$iv2 < 128 ? 1 : 0;
                                if ($i$f$isFull != 0) {
                                    int index$iv$iv$iv = (i$iv$iv$iv5 << 3) + j$iv$iv$iv4;
                                    valueToScopes2 = valueToScopes3;
                                    Object value2 = elements$iv$iv3[index$iv$iv$iv];
                                    if (value2 instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                        hasValues2 = hasValues5;
                                        if (!((StateObjectImpl) value2).m4558isReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(2))) {
                                            this_$iv$iv2 = this_$iv$iv3;
                                            m$iv$iv$iv3 = m$iv$iv$iv4;
                                            lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                            i$iv$iv$iv3 = i$iv$iv$iv5;
                                            j$iv$iv$iv3 = j$iv$iv$iv4;
                                            this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                            hasValues5 = hasValues2;
                                        }
                                    } else {
                                        hasValues2 = hasValues5;
                                    }
                                    if (this.readingDerivedStates || !ScopeMap.m4470containsimpl(mutableScatterMap4, value2)) {
                                        value$iv2 = value2;
                                        this_$iv$iv2 = this_$iv$iv3;
                                        m$iv$iv$iv3 = m$iv$iv$iv4;
                                        lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                        i$iv$iv$iv3 = i$iv$iv$iv5;
                                        j$iv$iv$iv3 = j$iv$iv$iv4;
                                        this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                        hasValues5 = hasValues2;
                                    } else {
                                        this.readingDerivedStates = true;
                                        MutableScatterMap<Object, Object> mutableScatterMap5 = mutableScatterMap4;
                                        try {
                                            Object value$iv3 = mutableScatterMap5.get(value2);
                                            if (value$iv3 != null) {
                                                value$iv2 = value2;
                                                try {
                                                    if (value$iv3 instanceof MutableScatterSet) {
                                                        ScatterSet this_$iv$iv4 = (MutableScatterSet) value$iv3;
                                                        Object[] elements$iv$iv4 = this_$iv$iv4.elements;
                                                        long[] m$iv$iv$iv5 = this_$iv$iv4.metadata;
                                                        int lastIndex$iv$iv$iv6 = m$iv$iv$iv5.length - 2;
                                                        int i$iv$iv$iv6 = 0;
                                                        if (0 <= lastIndex$iv$iv$iv6) {
                                                            while (true) {
                                                                long slot$iv$iv$iv3 = m$iv$iv$iv5[i$iv$iv$iv6];
                                                                MutableScatterMap<Object, Object> mutableScatterMap6 = mutableScatterMap5;
                                                                this_$iv$iv2 = this_$iv$iv3;
                                                                j$iv$iv$iv3 = j$iv$iv$iv4;
                                                                int index$iv$iv$iv2 = index$iv$iv$iv;
                                                                if ((((~slot$iv$iv$iv3) << 7) & slot$iv$iv$iv3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                    int bitCount$iv$iv$iv3 = 8 - ((~(i$iv$iv$iv6 - lastIndex$iv$iv$iv6)) >>> 31);
                                                                    int j$iv$iv$iv5 = 0;
                                                                    while (j$iv$iv$iv5 < bitCount$iv$iv$iv3) {
                                                                        long value$iv$iv$iv$iv3 = slot$iv$iv$iv3 & 255;
                                                                        if (value$iv$iv$iv$iv3 < 128) {
                                                                            int index$iv$iv$iv3 = (i$iv$iv$iv6 << 3) + j$iv$iv$iv5;
                                                                            try {
                                                                                DerivedState<?> derivedState = (DerivedState) elements$iv$iv4[index$iv$iv$iv3];
                                                                                Intrinsics.checkNotNull(derivedState, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
                                                                                Object previousValue2 = map.get(derivedState);
                                                                                SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
                                                                                if (policy == null) {
                                                                                    try {
                                                                                        policy = SnapshotStateKt.structuralEqualityPolicy();
                                                                                    } catch (Throwable th) {
                                                                                        th = th;
                                                                                        z3 = false;
                                                                                        this.readingDerivedStates = z3;
                                                                                        throw th;
                                                                                    }
                                                                                }
                                                                                try {
                                                                                    SnapshotMutationPolicy<?> snapshotMutationPolicy2 = policy;
                                                                                    if (snapshotMutationPolicy2.equivalent(derivedState.getCurrentRecord().getCurrentValue(), previousValue2)) {
                                                                                        lastIndex$iv$iv$iv4 = lastIndex$iv$iv$iv5;
                                                                                        i$iv$iv$iv4 = i$iv$iv$iv5;
                                                                                        this_$iv$iv$iv3 = this_$iv$iv$iv4;
                                                                                        Boolean.valueOf(this.statesToReread.add(derivedState));
                                                                                    } else {
                                                                                        Object value$iv4 = valueToScopes2.get(derivedState);
                                                                                        if (value$iv4 == null) {
                                                                                            lastIndex$iv$iv$iv4 = lastIndex$iv$iv$iv5;
                                                                                            i$iv$iv$iv4 = i$iv$iv$iv5;
                                                                                            this_$iv$iv$iv3 = this_$iv$iv$iv4;
                                                                                        } else if (value$iv4 instanceof MutableScatterSet) {
                                                                                            ScatterSet this_$iv$iv5 = (MutableScatterSet) value$iv4;
                                                                                            Object[] elements$iv$iv5 = this_$iv$iv5.elements;
                                                                                            long[] m$iv$iv$iv6 = this_$iv$iv5.metadata;
                                                                                            int lastIndex$iv$iv$iv7 = m$iv$iv$iv6.length - 2;
                                                                                            int i$iv$iv$iv7 = 0;
                                                                                            if (0 <= lastIndex$iv$iv$iv7) {
                                                                                                while (true) {
                                                                                                    long slot$iv$iv$iv4 = m$iv$iv$iv6[i$iv$iv$iv7];
                                                                                                    SnapshotMutationPolicy<?> snapshotMutationPolicy3 = snapshotMutationPolicy2;
                                                                                                    this_$iv$iv$iv3 = this_$iv$iv$iv4;
                                                                                                    lastIndex$iv$iv$iv4 = lastIndex$iv$iv$iv5;
                                                                                                    i$iv$iv$iv4 = i$iv$iv$iv5;
                                                                                                    if ((((~slot$iv$iv$iv4) << 7) & slot$iv$iv$iv4 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                                                                        if (i$iv$iv$iv7 != lastIndex$iv$iv$iv7) {
                                                                                                            break;
                                                                                                            break;
                                                                                                        }
                                                                                                        i$iv$iv$iv7++;
                                                                                                        lastIndex$iv$iv$iv5 = lastIndex$iv$iv$iv4;
                                                                                                        i$iv$iv$iv5 = i$iv$iv$iv4;
                                                                                                        this_$iv$iv$iv4 = this_$iv$iv$iv3;
                                                                                                        snapshotMutationPolicy2 = snapshotMutationPolicy3;
                                                                                                        i5 = 8;
                                                                                                    } else {
                                                                                                        int bitCount$iv$iv$iv4 = 8 - ((~(i$iv$iv$iv7 - lastIndex$iv$iv$iv7)) >>> 31);
                                                                                                        for (int j$iv$iv$iv6 = 0; j$iv$iv$iv6 < bitCount$iv$iv$iv4; j$iv$iv$iv6++) {
                                                                                                            long value$iv$iv$iv$iv4 = slot$iv$iv$iv4 & 255;
                                                                                                            if (value$iv$iv$iv$iv4 < 128) {
                                                                                                                int index$iv$iv$iv4 = (i$iv$iv$iv7 << 3) + j$iv$iv$iv6;
                                                                                                                try {
                                                                                                                    Object scope = elements$iv$iv5[index$iv$iv$iv4];
                                                                                                                    mutableScatterSet4.add(scope);
                                                                                                                    hasValues2 = true;
                                                                                                                } catch (Throwable th2) {
                                                                                                                    th = th2;
                                                                                                                    z3 = false;
                                                                                                                    this.readingDerivedStates = z3;
                                                                                                                    throw th;
                                                                                                                }
                                                                                                            }
                                                                                                            slot$iv$iv$iv4 >>= i5;
                                                                                                        }
                                                                                                        int j$iv$iv$iv7 = i5;
                                                                                                        if (bitCount$iv$iv$iv4 != j$iv$iv$iv7) {
                                                                                                            break;
                                                                                                        }
                                                                                                        if (i$iv$iv$iv7 != lastIndex$iv$iv$iv7) {
                                                                                                            break;
                                                                                                        }
                                                                                                        i$iv$iv$iv7++;
                                                                                                        lastIndex$iv$iv$iv5 = lastIndex$iv$iv$iv4;
                                                                                                        i$iv$iv$iv5 = i$iv$iv$iv4;
                                                                                                        this_$iv$iv$iv4 = this_$iv$iv$iv3;
                                                                                                        snapshotMutationPolicy2 = snapshotMutationPolicy3;
                                                                                                        i5 = 8;
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                lastIndex$iv$iv$iv4 = lastIndex$iv$iv$iv5;
                                                                                                i$iv$iv$iv4 = i$iv$iv$iv5;
                                                                                                this_$iv$iv$iv3 = this_$iv$iv$iv4;
                                                                                            }
                                                                                        } else {
                                                                                            lastIndex$iv$iv$iv4 = lastIndex$iv$iv$iv5;
                                                                                            i$iv$iv$iv4 = i$iv$iv$iv5;
                                                                                            this_$iv$iv$iv3 = this_$iv$iv$iv4;
                                                                                            mutableScatterSet4.add(value$iv4);
                                                                                            hasValues2 = true;
                                                                                        }
                                                                                        Unit unit = Unit.INSTANCE;
                                                                                    }
                                                                                } catch (Throwable th3) {
                                                                                    th = th3;
                                                                                    z3 = false;
                                                                                }
                                                                            } catch (Throwable th4) {
                                                                                th = th4;
                                                                                z3 = false;
                                                                            }
                                                                        } else {
                                                                            lastIndex$iv$iv$iv4 = lastIndex$iv$iv$iv5;
                                                                            i$iv$iv$iv4 = i$iv$iv$iv5;
                                                                            this_$iv$iv$iv3 = this_$iv$iv$iv4;
                                                                        }
                                                                        slot$iv$iv$iv3 >>= 8;
                                                                        j$iv$iv$iv5++;
                                                                        i5 = 8;
                                                                        m$iv$iv$iv4 = m$iv$iv$iv4;
                                                                        lastIndex$iv$iv$iv5 = lastIndex$iv$iv$iv4;
                                                                        i$iv$iv$iv5 = i$iv$iv$iv4;
                                                                        this_$iv$iv$iv4 = this_$iv$iv$iv3;
                                                                    }
                                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                                    lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                                                    i$iv$iv$iv3 = i$iv$iv$iv5;
                                                                    this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                                                    int i$iv$iv$iv8 = i5;
                                                                    if (bitCount$iv$iv$iv3 != i$iv$iv$iv8) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                                    lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                                                    i$iv$iv$iv3 = i$iv$iv$iv5;
                                                                    this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                                                }
                                                                if (i$iv$iv$iv6 != lastIndex$iv$iv$iv6) {
                                                                    i$iv$iv$iv6++;
                                                                    j$iv$iv$iv4 = j$iv$iv$iv3;
                                                                    index$iv$iv$iv = index$iv$iv$iv2;
                                                                    this_$iv$iv3 = this_$iv$iv2;
                                                                    mutableScatterMap5 = mutableScatterMap6;
                                                                    m$iv$iv$iv4 = m$iv$iv$iv3;
                                                                    lastIndex$iv$iv$iv5 = lastIndex$iv$iv$iv3;
                                                                    i$iv$iv$iv5 = i$iv$iv$iv3;
                                                                    this_$iv$iv$iv4 = this_$iv$iv$iv2;
                                                                    i5 = 8;
                                                                }
                                                            }
                                                            hasValues5 = hasValues2;
                                                        } else {
                                                            this_$iv$iv2 = this_$iv$iv3;
                                                            m$iv$iv$iv3 = m$iv$iv$iv4;
                                                            lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                                            i$iv$iv$iv3 = i$iv$iv$iv5;
                                                            j$iv$iv$iv3 = j$iv$iv$iv4;
                                                            this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                                        }
                                                        hasValues2 = hasValues2;
                                                        hasValues5 = hasValues2;
                                                    } else {
                                                        this_$iv$iv2 = this_$iv$iv3;
                                                        m$iv$iv$iv3 = m$iv$iv$iv4;
                                                        lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                                        i$iv$iv$iv3 = i$iv$iv$iv5;
                                                        j$iv$iv$iv3 = j$iv$iv$iv4;
                                                        this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                                        DerivedState<?> derivedState2 = (DerivedState) value$iv3;
                                                        Object previousValue3 = map.get(derivedState2);
                                                        SnapshotMutationPolicy<?> policy2 = derivedState2.getPolicy();
                                                        if (policy2 == null) {
                                                            policy2 = SnapshotStateKt.structuralEqualityPolicy();
                                                        }
                                                        if (policy2.equivalent(derivedState2.getCurrentRecord().getCurrentValue(), previousValue3)) {
                                                            Boolean.valueOf(this.statesToReread.add(derivedState2));
                                                            hasValues5 = hasValues2;
                                                        } else {
                                                            DerivedState<?> derivedState3 = derivedState2;
                                                            MutableScatterMap mutableScatterMap7 = valueToScopes2;
                                                            int i6 = 0;
                                                            Object value$iv5 = mutableScatterMap7.get(derivedState3);
                                                            if (value$iv5 == null) {
                                                                hasValues5 = hasValues2;
                                                            } else if (value$iv5 instanceof MutableScatterSet) {
                                                                ScatterSet this_$iv$iv6 = (MutableScatterSet) value$iv5;
                                                                Object[] elements$iv$iv6 = this_$iv$iv6.elements;
                                                                long[] m$iv$iv$iv7 = this_$iv$iv6.metadata;
                                                                int lastIndex$iv$iv$iv8 = m$iv$iv$iv7.length - 2;
                                                                int i$iv$iv$iv9 = 0;
                                                                if (0 <= lastIndex$iv$iv$iv8) {
                                                                    while (true) {
                                                                        long slot$iv$iv$iv5 = m$iv$iv$iv7[i$iv$iv$iv9];
                                                                        DerivedState<?> derivedState4 = derivedState3;
                                                                        MutableScatterMap mutableScatterMap8 = mutableScatterMap7;
                                                                        int i7 = i6;
                                                                        Object value$iv6 = value$iv5;
                                                                        if ((((~slot$iv$iv$iv5) << 7) & slot$iv$iv$iv5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                            int bitCount$iv$iv$iv5 = 8 - ((~(i$iv$iv$iv9 - lastIndex$iv$iv$iv8)) >>> 31);
                                                                            int j$iv$iv$iv8 = 0;
                                                                            while (j$iv$iv$iv8 < bitCount$iv$iv$iv5) {
                                                                                long value$iv$iv$iv$iv5 = slot$iv$iv$iv5 & 255;
                                                                                if (value$iv$iv$iv$iv5 < 128) {
                                                                                    int index$iv$iv$iv5 = (i$iv$iv$iv9 << 3) + j$iv$iv$iv8;
                                                                                    Object scope2 = elements$iv$iv6[index$iv$iv$iv5];
                                                                                    mutableScatterSet4.add(scope2);
                                                                                    hasValues2 = true;
                                                                                }
                                                                                slot$iv$iv$iv5 >>= 8;
                                                                                j$iv$iv$iv8++;
                                                                                policy2 = policy2;
                                                                            }
                                                                            snapshotMutationPolicy = policy2;
                                                                            if (bitCount$iv$iv$iv5 != 8) {
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            snapshotMutationPolicy = policy2;
                                                                        }
                                                                        if (i$iv$iv$iv9 != lastIndex$iv$iv$iv8) {
                                                                            i$iv$iv$iv9++;
                                                                            i6 = i7;
                                                                            value$iv5 = value$iv6;
                                                                            mutableScatterMap7 = mutableScatterMap8;
                                                                            derivedState3 = derivedState4;
                                                                            policy2 = snapshotMutationPolicy;
                                                                        }
                                                                    }
                                                                    hasValues5 = hasValues2;
                                                                }
                                                                hasValues2 = hasValues2;
                                                                hasValues5 = hasValues2;
                                                            } else {
                                                                mutableScatterSet4.add(value$iv5);
                                                                hasValues5 = true;
                                                            }
                                                            try {
                                                                Unit unit2 = Unit.INSTANCE;
                                                            } catch (Throwable th5) {
                                                                th = th5;
                                                                z3 = false;
                                                                this.readingDerivedStates = z3;
                                                                throw th;
                                                            }
                                                        }
                                                    }
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                    z3 = false;
                                                }
                                            } else {
                                                this_$iv$iv2 = this_$iv$iv3;
                                                m$iv$iv$iv3 = m$iv$iv$iv4;
                                                lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                                i$iv$iv$iv3 = i$iv$iv$iv5;
                                                j$iv$iv$iv3 = j$iv$iv$iv4;
                                                this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                                value$iv2 = value2;
                                                hasValues5 = hasValues2;
                                            }
                                            this.readingDerivedStates = false;
                                        } catch (Throwable th7) {
                                            th = th7;
                                            z3 = false;
                                        }
                                    }
                                    int i8 = 0;
                                    Object value$iv7 = valueToScopes2.get(value$iv2);
                                    if (value$iv7 != null) {
                                        if (value$iv7 instanceof MutableScatterSet) {
                                            ScatterSet this_$iv$iv7 = (MutableScatterSet) value$iv7;
                                            Object[] elements$iv$iv7 = this_$iv$iv7.elements;
                                            long[] m$iv$iv$iv8 = this_$iv$iv7.metadata;
                                            int lastIndex$iv$iv$iv9 = m$iv$iv$iv8.length - 2;
                                            int i$iv$iv$iv10 = 0;
                                            if (0 <= lastIndex$iv$iv$iv9) {
                                                while (true) {
                                                    long slot$iv$iv$iv6 = m$iv$iv$iv8[i$iv$iv$iv10];
                                                    hasValues3 = hasValues5;
                                                    int i9 = i8;
                                                    Object value$iv8 = value$iv7;
                                                    ScatterSet this_$iv$iv8 = this_$iv$iv7;
                                                    if ((((~slot$iv$iv$iv6) << 7) & slot$iv$iv$iv6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int bitCount$iv$iv$iv6 = 8 - ((~(i$iv$iv$iv10 - lastIndex$iv$iv$iv9)) >>> 31);
                                                        for (int j$iv$iv$iv9 = 0; j$iv$iv$iv9 < bitCount$iv$iv$iv6; j$iv$iv$iv9++) {
                                                            long value$iv$iv$iv$iv6 = slot$iv$iv$iv6 & 255;
                                                            int $i$f$isFull2 = value$iv$iv$iv$iv6 < 128 ? 1 : 0;
                                                            if ($i$f$isFull2 != 0) {
                                                                int index$iv$iv$iv6 = (i$iv$iv$iv10 << 3) + j$iv$iv$iv9;
                                                                Object scope3 = elements$iv$iv7[index$iv$iv$iv6];
                                                                mutableScatterSet4.add(scope3);
                                                                hasValues3 = true;
                                                            }
                                                            slot$iv$iv$iv6 >>= 8;
                                                        }
                                                        if (bitCount$iv$iv$iv6 != 8) {
                                                            break;
                                                        }
                                                        hasValues5 = hasValues3;
                                                    } else {
                                                        hasValues5 = hasValues3;
                                                    }
                                                    if (i$iv$iv$iv10 != lastIndex$iv$iv$iv9) {
                                                        i$iv$iv$iv10++;
                                                        value$iv7 = value$iv8;
                                                        this_$iv$iv7 = this_$iv$iv8;
                                                        i8 = i9;
                                                    }
                                                }
                                                hasValues5 = hasValues3;
                                            }
                                            hasValues3 = hasValues5;
                                            hasValues5 = hasValues3;
                                        } else {
                                            mutableScatterSet4.add(value$iv7);
                                            hasValues5 = true;
                                        }
                                    }
                                } else {
                                    valueToScopes2 = valueToScopes3;
                                    this_$iv$iv2 = this_$iv$iv3;
                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                    lastIndex$iv$iv$iv3 = lastIndex$iv$iv$iv5;
                                    i$iv$iv$iv3 = i$iv$iv$iv5;
                                    j$iv$iv$iv3 = j$iv$iv$iv4;
                                    this_$iv$iv$iv2 = this_$iv$iv$iv4;
                                }
                                slot$iv$iv$iv2 >>= 8;
                                j$iv$iv$iv4 = j$iv$iv$iv3 + 1;
                                i5 = 8;
                                valueToScopes3 = valueToScopes2;
                                this_$iv$iv3 = this_$iv$iv2;
                                m$iv$iv$iv4 = m$iv$iv$iv3;
                                lastIndex$iv$iv$iv5 = lastIndex$iv$iv$iv3;
                                i$iv$iv$iv5 = i$iv$iv$iv3;
                                this_$iv$iv$iv4 = this_$iv$iv$iv2;
                            }
                            valueToScopes = valueToScopes3;
                            boolean hasValues6 = hasValues5;
                            this_$iv$iv = this_$iv$iv3;
                            m$iv$iv$iv2 = m$iv$iv$iv4;
                            lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv5;
                            i$iv$iv$iv2 = i$iv$iv$iv5;
                            this_$iv$iv$iv = this_$iv$iv$iv4;
                            int i$iv$iv$iv11 = i5;
                            if (bitCount$iv$iv$iv2 != i$iv$iv$iv11) {
                                hasValues = hasValues6;
                                break;
                            }
                            hasValues4 = hasValues6;
                        } else {
                            valueToScopes = valueToScopes3;
                            this_$iv$iv = this_$iv$iv3;
                            m$iv$iv$iv2 = m$iv$iv$iv4;
                            lastIndex$iv$iv$iv2 = lastIndex$iv$iv$iv5;
                            i$iv$iv$iv2 = i$iv$iv$iv5;
                            this_$iv$iv$iv = this_$iv$iv$iv4;
                        }
                        lastIndex$iv$iv$iv5 = lastIndex$iv$iv$iv2;
                        int i$iv$iv$iv12 = i$iv$iv$iv2;
                        if (i$iv$iv$iv12 != lastIndex$iv$iv$iv5) {
                            i$iv$iv$iv5 = i$iv$iv$iv12 + 1;
                            $i$f$forEach2 = $i$f$forEach3;
                            elements$iv$iv2 = elements$iv$iv3;
                            $this$fastForEach$iv = $this$fastForEach$iv2;
                            $i$f$fastForEach = $i$f$fastForEach2;
                            valueToScopes3 = valueToScopes;
                            this_$iv$iv3 = this_$iv$iv;
                            m$iv$iv$iv4 = m$iv$iv$iv2;
                            this_$iv$iv$iv4 = this_$iv$iv$iv;
                            i5 = 8;
                        }
                    }
                }
                hasValues = hasValues4;
            } else {
                Set $this$forEach$iv$iv3 = $this$fastForEach$iv;
                int $i$f$forEach4 = 0;
                Iterator it2 = $this$forEach$iv$iv3.iterator();
                boolean hasValues7 = false;
                while (it2.hasNext()) {
                    Object element$iv$iv = it2.next();
                    Object value3 = element$iv$iv;
                    int i10 = 0;
                    if (value3 instanceof StateObjectImpl) {
                        ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                        if (((StateObjectImpl) value3).m4558isReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(2))) {
                            if (this.readingDerivedStates && ScopeMap.m4470containsimpl(mutableScatterMap4, value3)) {
                                this.readingDerivedStates = true;
                                Object key$iv5 = value3;
                                try {
                                    Object value$iv9 = mutableScatterMap4.get(key$iv5);
                                    if (value$iv9 != null) {
                                        mutableScatterMap = mutableScatterMap4;
                                        try {
                                            if (value$iv9 instanceof MutableScatterSet) {
                                                try {
                                                    ScatterSet this_$iv$iv9 = (MutableScatterSet) value$iv9;
                                                    Object[] elements$iv$iv8 = this_$iv$iv9.elements;
                                                    long[] m$iv$iv$iv9 = this_$iv$iv9.metadata;
                                                    int lastIndex$iv$iv$iv10 = m$iv$iv$iv9.length - 2;
                                                    int i$iv$iv$iv13 = 0;
                                                    if (0 <= lastIndex$iv$iv$iv10) {
                                                        while (true) {
                                                            long slot$iv$iv$iv7 = m$iv$iv$iv9[i$iv$iv$iv13];
                                                            $i$f$forEach = $i$f$forEach4;
                                                            it = it2;
                                                            Object element$iv$iv2 = element$iv$iv;
                                                            value = value3;
                                                            if ((((~slot$iv$iv$iv7) << 7) & slot$iv$iv$iv7 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                int bitCount$iv$iv$iv7 = 8 - ((~(i$iv$iv$iv13 - lastIndex$iv$iv$iv10)) >>> 31);
                                                                int j$iv$iv$iv10 = 0;
                                                                while (j$iv$iv$iv10 < bitCount$iv$iv$iv7) {
                                                                    long value$iv$iv$iv$iv7 = slot$iv$iv$iv7 & 255;
                                                                    int $i$f$isFull3 = value$iv$iv$iv$iv7 < 128 ? 1 : 0;
                                                                    if ($i$f$isFull3 != 0) {
                                                                        int index$iv$iv$iv7 = (i$iv$iv$iv13 << 3) + j$iv$iv$iv10;
                                                                        try {
                                                                            DerivedState<?> derivedState5 = (DerivedState) elements$iv$iv8[index$iv$iv$iv7];
                                                                            $this$forEach$iv$iv2 = $this$forEach$iv$iv3;
                                                                            try {
                                                                                Intrinsics.checkNotNull(derivedState5, str3);
                                                                                Object previousValue4 = map.get(derivedState5);
                                                                                SnapshotMutationPolicy<?> policy3 = derivedState5.getPolicy();
                                                                                if (policy3 == null) {
                                                                                    try {
                                                                                        policy3 = SnapshotStateKt.structuralEqualityPolicy();
                                                                                    } catch (Throwable th8) {
                                                                                        th = th8;
                                                                                        z2 = false;
                                                                                        this.readingDerivedStates = z2;
                                                                                        throw th;
                                                                                    }
                                                                                }
                                                                                j$iv$iv$iv2 = j$iv$iv$iv10;
                                                                                boolean hasValues8 = hasValues7;
                                                                                SnapshotMutationPolicy<?> snapshotMutationPolicy4 = policy3;
                                                                                try {
                                                                                    if (snapshotMutationPolicy4.equivalent(derivedState5.getCurrentRecord().getCurrentValue(), previousValue4)) {
                                                                                        str2 = str3;
                                                                                        i4 = i10;
                                                                                        key$iv4 = key$iv5;
                                                                                        Boolean.valueOf(this.statesToReread.add(derivedState5));
                                                                                        hasValues7 = hasValues8;
                                                                                    } else {
                                                                                        Object value$iv10 = valueToScopes3.get(derivedState5);
                                                                                        if (value$iv10 == null) {
                                                                                            str2 = str3;
                                                                                            i4 = i10;
                                                                                            key$iv4 = key$iv5;
                                                                                            hasValues7 = hasValues8;
                                                                                        } else if (value$iv10 instanceof MutableScatterSet) {
                                                                                            ScatterSet this_$iv$iv10 = (MutableScatterSet) value$iv10;
                                                                                            Object[] elements$iv$iv9 = this_$iv$iv10.elements;
                                                                                            long[] m$iv$iv$iv10 = this_$iv$iv10.metadata;
                                                                                            int lastIndex$iv$iv$iv11 = m$iv$iv$iv10.length - 2;
                                                                                            int i$iv$iv$iv14 = 0;
                                                                                            if (0 <= lastIndex$iv$iv$iv11) {
                                                                                                while (true) {
                                                                                                    long slot$iv$iv$iv8 = m$iv$iv$iv10[i$iv$iv$iv14];
                                                                                                    SnapshotMutationPolicy<?> snapshotMutationPolicy5 = snapshotMutationPolicy4;
                                                                                                    str2 = str3;
                                                                                                    i4 = i10;
                                                                                                    key$iv4 = key$iv5;
                                                                                                    if ((((~slot$iv$iv$iv8) << 7) & slot$iv$iv$iv8 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                                                        int bitCount$iv$iv$iv8 = 8 - ((~(i$iv$iv$iv14 - lastIndex$iv$iv$iv11)) >>> 31);
                                                                                                        for (int j$iv$iv$iv11 = 0; j$iv$iv$iv11 < bitCount$iv$iv$iv8; j$iv$iv$iv11++) {
                                                                                                            long value$iv$iv$iv$iv8 = slot$iv$iv$iv8 & 255;
                                                                                                            if (value$iv$iv$iv$iv8 < 128) {
                                                                                                                int index$iv$iv$iv8 = (i$iv$iv$iv14 << 3) + j$iv$iv$iv11;
                                                                                                                try {
                                                                                                                    Object scope4 = elements$iv$iv9[index$iv$iv$iv8];
                                                                                                                    mutableScatterSet4.add(scope4);
                                                                                                                    hasValues8 = true;
                                                                                                                } catch (Throwable th9) {
                                                                                                                    th = th9;
                                                                                                                    z2 = false;
                                                                                                                    this.readingDerivedStates = z2;
                                                                                                                    throw th;
                                                                                                                }
                                                                                                            }
                                                                                                            slot$iv$iv$iv8 >>= 8;
                                                                                                        }
                                                                                                        if (bitCount$iv$iv$iv8 != 8) {
                                                                                                            break;
                                                                                                        }
                                                                                                    }
                                                                                                    if (i$iv$iv$iv14 != lastIndex$iv$iv$iv11) {
                                                                                                        i$iv$iv$iv14++;
                                                                                                        i10 = i4;
                                                                                                        key$iv5 = key$iv4;
                                                                                                        str3 = str2;
                                                                                                        snapshotMutationPolicy4 = snapshotMutationPolicy5;
                                                                                                    }
                                                                                                }
                                                                                                hasValues7 = hasValues8;
                                                                                            } else {
                                                                                                str2 = str3;
                                                                                                i4 = i10;
                                                                                                key$iv4 = key$iv5;
                                                                                            }
                                                                                            hasValues8 = hasValues8;
                                                                                            hasValues7 = hasValues8;
                                                                                        } else {
                                                                                            str2 = str3;
                                                                                            i4 = i10;
                                                                                            key$iv4 = key$iv5;
                                                                                            mutableScatterSet4.add(value$iv10);
                                                                                            hasValues7 = true;
                                                                                        }
                                                                                        try {
                                                                                            Unit unit3 = Unit.INSTANCE;
                                                                                        } catch (Throwable th10) {
                                                                                            th = th10;
                                                                                            z2 = false;
                                                                                            this.readingDerivedStates = z2;
                                                                                            throw th;
                                                                                        }
                                                                                    }
                                                                                } catch (Throwable th11) {
                                                                                    th = th11;
                                                                                    z2 = false;
                                                                                }
                                                                            } catch (Throwable th12) {
                                                                                th = th12;
                                                                                z2 = false;
                                                                            }
                                                                        } catch (Throwable th13) {
                                                                            th = th13;
                                                                            z2 = false;
                                                                        }
                                                                    } else {
                                                                        $this$forEach$iv$iv2 = $this$forEach$iv$iv3;
                                                                        j$iv$iv$iv2 = j$iv$iv$iv10;
                                                                        str2 = str3;
                                                                        i4 = i10;
                                                                        key$iv4 = key$iv5;
                                                                    }
                                                                    slot$iv$iv$iv7 >>= 8;
                                                                    j$iv$iv$iv10 = j$iv$iv$iv2 + 1;
                                                                    $this$forEach$iv$iv3 = $this$forEach$iv$iv2;
                                                                    i10 = i4;
                                                                    key$iv5 = key$iv4;
                                                                    str3 = str2;
                                                                }
                                                                $this$forEach$iv$iv = $this$forEach$iv$iv3;
                                                                str = str3;
                                                                i3 = i10;
                                                                key$iv3 = key$iv5;
                                                                hasValues7 = hasValues7;
                                                                if (bitCount$iv$iv$iv7 != 8) {
                                                                    break;
                                                                }
                                                            } else {
                                                                $this$forEach$iv$iv = $this$forEach$iv$iv3;
                                                                str = str3;
                                                                i3 = i10;
                                                                key$iv3 = key$iv5;
                                                            }
                                                            if (i$iv$iv$iv13 == lastIndex$iv$iv$iv10) {
                                                                break;
                                                            }
                                                            i$iv$iv$iv13++;
                                                            element$iv$iv = element$iv$iv2;
                                                            value3 = value;
                                                            $i$f$forEach4 = $i$f$forEach;
                                                            it2 = it;
                                                            $this$forEach$iv$iv3 = $this$forEach$iv$iv;
                                                            i10 = i3;
                                                            key$iv5 = key$iv3;
                                                            str3 = str;
                                                        }
                                                    } else {
                                                        $this$forEach$iv$iv = $this$forEach$iv$iv3;
                                                        $i$f$forEach = $i$f$forEach4;
                                                        it = it2;
                                                        str = str3;
                                                        value = value3;
                                                    }
                                                    map = map;
                                                } catch (Throwable th14) {
                                                    th = th14;
                                                    z2 = false;
                                                }
                                            } else {
                                                $this$forEach$iv$iv = $this$forEach$iv$iv3;
                                                $i$f$forEach = $i$f$forEach4;
                                                it = it2;
                                                str = str3;
                                                value = value3;
                                                try {
                                                    DerivedState<?> derivedState6 = (DerivedState) value$iv9;
                                                    Object previousValue5 = map.get(derivedState6);
                                                    SnapshotMutationPolicy<?> policy4 = derivedState6.getPolicy();
                                                    if (policy4 == null) {
                                                        policy4 = SnapshotStateKt.structuralEqualityPolicy();
                                                    }
                                                    if (policy4.equivalent(derivedState6.getCurrentRecord().getCurrentValue(), previousValue5)) {
                                                        map = map;
                                                        Boolean.valueOf(this.statesToReread.add(derivedState6));
                                                    } else {
                                                        DerivedState<?> derivedState7 = derivedState6;
                                                        MutableScatterMap mutableScatterMap9 = valueToScopes3;
                                                        Object value$iv11 = mutableScatterMap9.get(derivedState7);
                                                        if (value$iv11 != null) {
                                                            if (value$iv11 instanceof MutableScatterSet) {
                                                                ScatterSet this_$iv$iv11 = (MutableScatterSet) value$iv11;
                                                                Object[] elements$iv$iv10 = this_$iv$iv11.elements;
                                                                try {
                                                                    long[] m$iv$iv$iv11 = this_$iv$iv11.metadata;
                                                                    int lastIndex$iv$iv$iv12 = m$iv$iv$iv11.length - 2;
                                                                    int i$iv$iv$iv15 = 0;
                                                                    if (0 <= lastIndex$iv$iv$iv12) {
                                                                        while (true) {
                                                                            try {
                                                                                long slot$iv$iv$iv9 = m$iv$iv$iv11[i$iv$iv$iv15];
                                                                                SnapshotMutationPolicy<?> snapshotMutationPolicy6 = policy4;
                                                                                DerivedState<?> derivedState8 = derivedState7;
                                                                                boolean hasValues9 = hasValues7;
                                                                                MutableScatterMap mutableScatterMap10 = mutableScatterMap9;
                                                                                if ((((~slot$iv$iv$iv9) << 7) & slot$iv$iv$iv9 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                                    int bitCount$iv$iv$iv9 = 8 - ((~(i$iv$iv$iv15 - lastIndex$iv$iv$iv12)) >>> 31);
                                                                                    int j$iv$iv$iv12 = 0;
                                                                                    hasValues7 = hasValues9;
                                                                                    while (j$iv$iv$iv12 < bitCount$iv$iv$iv9) {
                                                                                        long value$iv$iv$iv$iv9 = slot$iv$iv$iv9 & 255;
                                                                                        int $i$f$isFull4 = value$iv$iv$iv$iv9 < 128 ? 1 : 0;
                                                                                        if ($i$f$isFull4 != 0) {
                                                                                            int index$iv$iv$iv9 = (i$iv$iv$iv15 << 3) + j$iv$iv$iv12;
                                                                                            Object scope5 = elements$iv$iv10[index$iv$iv$iv9];
                                                                                            mutableScatterSet4.add(scope5);
                                                                                            hasValues7 = true;
                                                                                        }
                                                                                        slot$iv$iv$iv9 >>= 8;
                                                                                        j$iv$iv$iv12++;
                                                                                        previousValue5 = previousValue5;
                                                                                    }
                                                                                    previousValue = previousValue5;
                                                                                    if (bitCount$iv$iv$iv9 != 8) {
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    previousValue = previousValue5;
                                                                                    hasValues7 = hasValues9;
                                                                                }
                                                                                if (i$iv$iv$iv15 == lastIndex$iv$iv$iv12) {
                                                                                    break;
                                                                                }
                                                                                i$iv$iv$iv15++;
                                                                                mutableScatterMap9 = mutableScatterMap10;
                                                                                policy4 = snapshotMutationPolicy6;
                                                                                derivedState7 = derivedState8;
                                                                                previousValue5 = previousValue;
                                                                            } catch (Throwable th15) {
                                                                                th = th15;
                                                                                z2 = false;
                                                                                this.readingDerivedStates = z2;
                                                                                throw th;
                                                                            }
                                                                        }
                                                                    }
                                                                } catch (Throwable th16) {
                                                                    th = th16;
                                                                    z2 = false;
                                                                }
                                                            } else {
                                                                mutableScatterSet4.add(value$iv11);
                                                                hasValues7 = true;
                                                            }
                                                        }
                                                        Unit unit4 = Unit.INSTANCE;
                                                    }
                                                } catch (Throwable th17) {
                                                    th = th17;
                                                    z2 = false;
                                                }
                                            }
                                        } catch (Throwable th18) {
                                            th = th18;
                                            z2 = false;
                                        }
                                    } else {
                                        map = map;
                                        $this$forEach$iv$iv = $this$forEach$iv$iv3;
                                        $i$f$forEach = $i$f$forEach4;
                                        it = it2;
                                        str = str3;
                                        value = value3;
                                        mutableScatterMap = mutableScatterMap4;
                                    }
                                    this.readingDerivedStates = false;
                                } catch (Throwable th19) {
                                    th = th19;
                                    z2 = false;
                                }
                            } else {
                                mutableScatterMap = mutableScatterMap4;
                                map = map;
                                $this$forEach$iv$iv = $this$forEach$iv$iv3;
                                $i$f$forEach = $i$f$forEach4;
                                it = it2;
                                str = str3;
                                value = value3;
                            }
                            key$iv = value;
                            mutableScatterMap2 = valueToScopes3;
                            i = 0;
                            value$iv = mutableScatterMap2.get(key$iv);
                            if (value$iv != null) {
                                mutableScatterSet = mutableScatterSet4;
                            } else if (value$iv instanceof MutableScatterSet) {
                                ScatterSet this_$iv$iv12 = (MutableScatterSet) value$iv;
                                elements$iv$iv = this_$iv$iv12.elements;
                                m$iv$iv$iv = this_$iv$iv12.metadata;
                                lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                                i$iv$iv$iv = 0;
                                if (0 <= lastIndex$iv$iv$iv) {
                                    while (true) {
                                        slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                        mutableScatterSet2 = mutableScatterSet4;
                                        mutableScatterMap3 = mutableScatterMap2;
                                        i2 = i;
                                        if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                            bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                            j$iv$iv$iv = 0;
                                            while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                                value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                                if (value$iv$iv$iv$iv < 128) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                if (z) {
                                                    int index$iv$iv$iv10 = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                    mutableScatterSet3 = mutableScatterSet2;
                                                    mutableScatterSet3.add(elements$iv$iv[index$iv$iv$iv10]);
                                                    hasValues7 = true;
                                                } else {
                                                    mutableScatterSet3 = mutableScatterSet2;
                                                }
                                                slot$iv$iv$iv >>= 8;
                                                mutableScatterSet2 = mutableScatterSet3;
                                                j$iv$iv$iv++;
                                                key$iv = key$iv;
                                            }
                                            key$iv2 = key$iv;
                                            mutableScatterSet = mutableScatterSet2;
                                            if (bitCount$iv$iv$iv == 8) {
                                                break;
                                            }
                                        } else {
                                            key$iv2 = key$iv;
                                            mutableScatterSet = mutableScatterSet2;
                                        }
                                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                                            break;
                                        }
                                        i$iv$iv$iv++;
                                        mutableScatterSet4 = mutableScatterSet;
                                        mutableScatterMap2 = mutableScatterMap3;
                                        i = i2;
                                        key$iv = key$iv2;
                                    }
                                } else {
                                    mutableScatterSet = mutableScatterSet4;
                                }
                            } else {
                                mutableScatterSet = mutableScatterSet4;
                                mutableScatterSet.add(value$iv);
                                hasValues7 = true;
                            }
                        } else {
                            mutableScatterMap = mutableScatterMap4;
                            map = map;
                            $this$forEach$iv$iv = $this$forEach$iv$iv3;
                            mutableScatterSet = mutableScatterSet4;
                            $i$f$forEach = $i$f$forEach4;
                            it = it2;
                            str = str3;
                        }
                    } else {
                        if (this.readingDerivedStates) {
                            mutableScatterMap = mutableScatterMap4;
                            map = map;
                            $this$forEach$iv$iv = $this$forEach$iv$iv3;
                            $i$f$forEach = $i$f$forEach4;
                            it = it2;
                            str = str3;
                            value = value3;
                        } else {
                            mutableScatterMap = mutableScatterMap4;
                            map = map;
                            $this$forEach$iv$iv = $this$forEach$iv$iv3;
                            $i$f$forEach = $i$f$forEach4;
                            it = it2;
                            str = str3;
                            value = value3;
                        }
                        key$iv = value;
                        mutableScatterMap2 = valueToScopes3;
                        i = 0;
                        value$iv = mutableScatterMap2.get(key$iv);
                        if (value$iv != null) {
                            mutableScatterSet = mutableScatterSet4;
                        } else if (value$iv instanceof MutableScatterSet) {
                            ScatterSet this_$iv$iv13 = (MutableScatterSet) value$iv;
                            elements$iv$iv = this_$iv$iv13.elements;
                            m$iv$iv$iv = this_$iv$iv13.metadata;
                            lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                            i$iv$iv$iv = 0;
                            if (0 <= lastIndex$iv$iv$iv) {
                                while (true) {
                                    slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                    mutableScatterSet2 = mutableScatterSet4;
                                    mutableScatterMap3 = mutableScatterMap2;
                                    i2 = i;
                                    if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                        bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                        j$iv$iv$iv = 0;
                                        while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                            value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                            if (value$iv$iv$iv$iv < 128) {
                                                z = true;
                                            } else {
                                                z = false;
                                            }
                                            if (z) {
                                                int index$iv$iv$iv11 = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                mutableScatterSet3 = mutableScatterSet2;
                                                mutableScatterSet3.add(elements$iv$iv[index$iv$iv$iv11]);
                                                hasValues7 = true;
                                            } else {
                                                mutableScatterSet3 = mutableScatterSet2;
                                            }
                                            slot$iv$iv$iv >>= 8;
                                            mutableScatterSet2 = mutableScatterSet3;
                                            j$iv$iv$iv++;
                                            key$iv = key$iv;
                                        }
                                        key$iv2 = key$iv;
                                        mutableScatterSet = mutableScatterSet2;
                                        if (bitCount$iv$iv$iv == 8) {
                                            break;
                                            break;
                                        }
                                    } else {
                                        key$iv2 = key$iv;
                                        mutableScatterSet = mutableScatterSet2;
                                    }
                                    if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                                        break;
                                        break;
                                    }
                                    i$iv$iv$iv++;
                                    mutableScatterSet4 = mutableScatterSet;
                                    mutableScatterMap2 = mutableScatterMap3;
                                    i = i2;
                                    key$iv = key$iv2;
                                }
                            } else {
                                mutableScatterSet = mutableScatterSet4;
                            }
                        } else {
                            mutableScatterSet = mutableScatterSet4;
                            mutableScatterSet.add(value$iv);
                            hasValues7 = true;
                        }
                    }
                    mutableScatterSet4 = mutableScatterSet;
                    mutableScatterMap4 = mutableScatterMap;
                    map = map;
                    $i$f$forEach4 = $i$f$forEach;
                    it2 = it;
                    $this$forEach$iv$iv3 = $this$forEach$iv$iv;
                    str3 = str;
                }
                hasValues = hasValues7;
            }
            if (!this.readingDerivedStates) {
                if (this.statesToReread.getSize() != 0) {
                    MutableVector<DerivedState<?>> mutableVector = this.statesToReread;
                    Object[] content$iv = mutableVector.content;
                    int size$iv = mutableVector.getSize();
                    for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                        rereadDerivedState((DerivedState) content$iv[i$iv]);
                    }
                    this.statesToReread.clear();
                }
            }
            return hasValues;
        }

        public final void rereadDerivedState(DerivedState<?> derivedState) {
            ScatterSet this_$iv$iv;
            long[] m$iv$iv$iv;
            MutableObjectIntMap<Object> mutableObjectIntMap;
            MutableScatterMap<Object, MutableObjectIntMap<Object>> mutableScatterMap = this.scopeToValues;
            int token = Long.hashCode(SnapshotKt.currentSnapshot().getSnapshotId());
            MutableScatterMap<Object, Object> mutableScatterMap2 = this.valueToScopes;
            Object key$iv = derivedState;
            int i = 0;
            Object value$iv = mutableScatterMap2.get(key$iv);
            if (value$iv == null) {
                return;
            }
            if (!(value$iv instanceof MutableScatterSet)) {
                MutableObjectIntMap<Object> mutableObjectIntMap2 = mutableScatterMap.get(value$iv);
                if (mutableObjectIntMap2 == null) {
                    mutableObjectIntMap2 = new MutableObjectIntMap<>(0, 1, null);
                    mutableScatterMap.set(value$iv, mutableObjectIntMap2);
                    Unit unit = Unit.INSTANCE;
                }
                recordRead(derivedState, token, value$iv, mutableObjectIntMap2);
                return;
            }
            ScatterSet this_$iv$iv2 = (MutableScatterSet) value$iv;
            Object[] elements$iv$iv = this_$iv$iv2.elements;
            long[] m$iv$iv$iv2 = this_$iv$iv2.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv2.length - 2;
            int i$iv$iv$iv = 0;
            if (0 > lastIndex$iv$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv2[i$iv$iv$iv];
                MutableScatterMap<Object, Object> mutableScatterMap3 = mutableScatterMap2;
                Object key$iv2 = key$iv;
                int i2 = i;
                Object value$iv2 = value$iv;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv = 0;
                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull != 0) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object scope = elements$iv$iv[index$iv$iv$iv];
                            MutableObjectIntMap<Object> mutableObjectIntMap3 = mutableScatterMap.get(scope);
                            if (mutableObjectIntMap3 == null) {
                                mutableObjectIntMap = new MutableObjectIntMap<>(0, 1, null);
                                mutableScatterMap.set(scope, mutableObjectIntMap);
                                Unit unit2 = Unit.INSTANCE;
                            } else {
                                mutableObjectIntMap = mutableObjectIntMap3;
                            }
                            recordRead(derivedState, token, scope, mutableObjectIntMap);
                        } else {
                            j$iv$iv$iv = j$iv$iv$iv;
                            this_$iv$iv2 = this_$iv$iv2;
                            m$iv$iv$iv2 = m$iv$iv$iv2;
                        }
                        slot$iv$iv$iv >>= i3;
                        j$iv$iv$iv++;
                        i3 = i3;
                        this_$iv$iv2 = this_$iv$iv2;
                        m$iv$iv$iv2 = m$iv$iv$iv2;
                    }
                    this_$iv$iv = this_$iv$iv2;
                    m$iv$iv$iv = m$iv$iv$iv2;
                    if (bitCount$iv$iv$iv != i3) {
                        return;
                    }
                } else {
                    this_$iv$iv = this_$iv$iv2;
                    m$iv$iv$iv = m$iv$iv$iv2;
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    return;
                }
                i$iv$iv$iv++;
                i = i2;
                value$iv = value$iv2;
                mutableScatterMap2 = mutableScatterMap3;
                key$iv = key$iv2;
                this_$iv$iv2 = this_$iv$iv;
                m$iv$iv$iv2 = m$iv$iv$iv;
            }
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0068 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:19:0x006a A[LOOP:0: B:4:0x0018->B:19:0x006a, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:25:0x0074 A[EDGE_INSN: B:25:0x0074->B:22:0x0074 BREAK  A[LOOP:0: B:4:0x0018->B:19:0x006a], SYNTHETIC] */
        public final void notifyInvalidatedScopes() {
            MutableScatterSet<Object> mutableScatterSet;
            MutableScatterSet<Object> mutableScatterSet2 = this.invalidated;
            MutableScatterSet<Object> this_$iv = mutableScatterSet2;
            Function1<Object, Unit> function1 = this.onChanged;
            Object[] elements$iv = this_$iv.elements;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    mutableScatterSet = mutableScatterSet2;
                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i$iv$iv != lastIndex$iv$iv) {
                            break;
                            break;
                        } else {
                            i$iv$iv++;
                            mutableScatterSet2 = mutableScatterSet;
                        }
                    } else {
                        int i = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (value$iv$iv$iv < 128) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                function1.invoke(elements$iv[index$iv$iv]);
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i = i;
                        }
                        if (bitCount$iv$iv != i) {
                            break;
                        }
                        if (i$iv$iv != lastIndex$iv$iv) {
                            break;
                        }
                        i$iv$iv++;
                        mutableScatterSet2 = mutableScatterSet;
                    }
                }
            } else {
                mutableScatterSet = mutableScatterSet2;
            }
            mutableScatterSet.clear();
        }
    }
}
