package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes12.dex */
public class View {
    private boolean current;
    private DocumentSet documentSet;
    private final Query query;
    private ImmutableSortedSet<DocumentKey> syncedDocuments;
    private ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    private ImmutableSortedSet<DocumentKey> limboDocuments = DocumentKey.emptyKeySet();
    private ImmutableSortedSet<DocumentKey> mutatedKeys = DocumentKey.emptyKeySet();

    public static class DocumentChanges {
        final DocumentViewChangeSet changeSet;
        final DocumentSet documentSet;
        final ImmutableSortedSet<DocumentKey> mutatedKeys;
        private final boolean needsRefill;

        private DocumentChanges(DocumentSet newDocuments, DocumentViewChangeSet changes, ImmutableSortedSet<DocumentKey> mutatedKeys, boolean needsRefill) {
            this.documentSet = newDocuments;
            this.changeSet = changes;
            this.mutatedKeys = mutatedKeys;
            this.needsRefill = needsRefill;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }
    }

    public View(Query query, ImmutableSortedSet<DocumentKey> remoteDocuments) {
        this.query = query;
        this.documentSet = DocumentSet.emptySet(query.comparator());
        this.syncedDocuments = remoteDocuments;
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> docChanges) {
        return computeDocChanges(docChanges, null);
    }

    /* JADX WARN: Code duplicated, block: B:65:0x012a  */
    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> docChanges, DocumentChanges previousChanges) {
        Document lastDocument;
        Document firstDocument;
        ImmutableSortedSet<DocumentKey> newMutatedKeys;
        Document oldDoc;
        DocumentSet oldDocumentSet;
        DocumentViewChangeSet changeSet = previousChanges != null ? previousChanges.changeSet : new DocumentViewChangeSet();
        DocumentSet oldDocumentSet2 = previousChanges != null ? previousChanges.documentSet : this.documentSet;
        ImmutableSortedSet<DocumentKey> newMutatedKeys2 = previousChanges != null ? previousChanges.mutatedKeys : this.mutatedKeys;
        DocumentSet newDocumentSet = oldDocumentSet2;
        boolean changeApplied = false;
        if (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST) && oldDocumentSet2.size() == this.query.getLimit()) {
            lastDocument = oldDocumentSet2.getLastDocument();
        } else {
            lastDocument = null;
        }
        Document lastDocInLimit = lastDocument;
        if (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_LAST) && oldDocumentSet2.size() == this.query.getLimit()) {
            firstDocument = oldDocumentSet2.getFirstDocument();
        } else {
            firstDocument = null;
        }
        Document firstDocInLimit = firstDocument;
        for (Map.Entry<DocumentKey, Document> entry : docChanges) {
            DocumentKey key = entry.getKey();
            Document oldDoc2 = oldDocumentSet2.getDocument(key);
            Document newDoc = this.query.matches(entry.getValue()) ? entry.getValue() : null;
            boolean oldDocHadPendingMutations = oldDoc2 != null && this.mutatedKeys.contains(oldDoc2.getKey());
            boolean newDocHasPendingMutations = newDoc != null && (newDoc.hasLocalMutations() || (this.mutatedKeys.contains(newDoc.getKey()) && newDoc.hasCommittedMutations()));
            boolean changeApplied2 = false;
            if (oldDoc2 == null || newDoc == null) {
                oldDocumentSet = oldDocumentSet2;
                boolean needsRefill = changeApplied;
                if (oldDoc2 == null && newDoc != null) {
                    changeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.ADDED, newDoc));
                    changeApplied2 = true;
                    changeApplied = needsRefill;
                } else if (oldDoc2 != null && newDoc == null) {
                    changeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, oldDoc2));
                    changeApplied2 = true;
                    changeApplied = (lastDocInLimit == null && firstDocInLimit == null) ? needsRefill : true;
                } else {
                    changeApplied = needsRefill;
                }
            } else {
                oldDocumentSet = oldDocumentSet2;
                boolean needsRefill2 = changeApplied;
                boolean docsEqual = oldDoc2.getData().equals(newDoc.getData());
                if (!docsEqual) {
                    if (!shouldWaitForSyncedDocument(oldDoc2, newDoc)) {
                        changeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, newDoc));
                        if ((lastDocInLimit != null && this.query.comparator().compare(newDoc, lastDocInLimit) > 0) || (firstDocInLimit != null && this.query.comparator().compare(newDoc, firstDocInLimit) < 0)) {
                            changeApplied2 = true;
                            changeApplied = true;
                        } else {
                            changeApplied2 = true;
                            changeApplied = needsRefill2;
                        }
                    } else {
                        changeApplied = needsRefill2;
                    }
                } else if (oldDocHadPendingMutations != newDocHasPendingMutations) {
                    changeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.METADATA, newDoc));
                    changeApplied2 = true;
                    changeApplied = needsRefill2;
                } else {
                    changeApplied = needsRefill2;
                }
            }
            if (changeApplied2) {
                if (newDoc != null) {
                    DocumentSet newDocumentSet2 = newDocumentSet.add(newDoc);
                    if (newDoc.hasLocalMutations()) {
                        newMutatedKeys2 = newMutatedKeys2.insert(newDoc.getKey());
                        newDocumentSet = newDocumentSet2;
                    } else {
                        newMutatedKeys2 = newMutatedKeys2.remove(newDoc.getKey());
                        newDocumentSet = newDocumentSet2;
                    }
                } else {
                    DocumentSet newDocumentSet3 = newDocumentSet.remove(key);
                    newMutatedKeys2 = newMutatedKeys2.remove(key);
                    newDocumentSet = newDocumentSet3;
                }
            }
            oldDocumentSet2 = oldDocumentSet;
        }
        boolean needsRefill3 = changeApplied;
        if (!this.query.hasLimit()) {
            newMutatedKeys = newMutatedKeys2;
        } else {
            long i = newDocumentSet.size();
            long limit = this.query.getLimit();
            while (true) {
                i -= limit;
                if (i <= 0) {
                    break;
                }
                if (this.query.getLimitType().equals(Query.LimitType.LIMIT_TO_FIRST)) {
                    oldDoc = newDocumentSet.getLastDocument();
                } else {
                    oldDoc = newDocumentSet.getFirstDocument();
                }
                newDocumentSet = newDocumentSet.remove(oldDoc.getKey());
                newMutatedKeys2 = newMutatedKeys2.remove(oldDoc.getKey());
                changeSet.addChange(DocumentViewChange.create(DocumentViewChange.Type.REMOVED, oldDoc));
                limit = 1;
            }
            newMutatedKeys = newMutatedKeys2;
        }
        Assert.hardAssert(!needsRefill3 || previousChanges == null, "View was refilled using docs that themselves needed refilling.", new Object[0]);
        return new DocumentChanges(newDocumentSet, changeSet, newMutatedKeys, needsRefill3);
    }

    private boolean shouldWaitForSyncedDocument(Document oldDoc, Document newDoc) {
        return oldDoc.hasLocalMutations() && newDoc.hasCommittedMutations() && !newDoc.hasLocalMutations();
    }

    public ViewChange applyChanges(DocumentChanges docChanges) {
        return applyChanges(docChanges, null);
    }

    public ViewChange applyChanges(DocumentChanges docChanges, TargetChange targetChange) {
        return applyChanges(docChanges, targetChange, false);
    }

    public ViewChange applyChanges(DocumentChanges docChanges, TargetChange targetChange, boolean targetIsPendingReset) {
        ViewSnapshot snapshot;
        Assert.hardAssert(!docChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet oldDocumentSet = this.documentSet;
        this.documentSet = docChanges.documentSet;
        this.mutatedKeys = docChanges.mutatedKeys;
        List<DocumentViewChange> viewChanges = docChanges.changeSet.getChanges();
        Collections.sort(viewChanges, new Comparator() { // from class: com.google.firebase.firestore.core.View$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.m8978lambda$applyChanges$0$comgooglefirebasefirestorecoreView((DocumentViewChange) obj, (DocumentViewChange) obj2);
            }
        });
        applyTargetChange(targetChange);
        List<LimboDocumentChange> limboDocumentChanges = targetIsPendingReset ? Collections.emptyList() : updateLimboDocuments();
        boolean synced = this.limboDocuments.size() == 0 && this.current && !targetIsPendingReset;
        ViewSnapshot.SyncState newSyncState = synced ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean syncStatedChanged = newSyncState != this.syncState;
        this.syncState = newSyncState;
        if (viewChanges.size() == 0 && !syncStatedChanged) {
            snapshot = null;
        } else {
            boolean fromCache = newSyncState == ViewSnapshot.SyncState.LOCAL;
            boolean hasCachedResults = (targetChange == null || targetChange.getResumeToken().isEmpty()) ? false : true;
            ViewSnapshot snapshot2 = new ViewSnapshot(this.query, docChanges.documentSet, oldDocumentSet, viewChanges, fromCache, docChanges.mutatedKeys, syncStatedChanged, false, hasCachedResults);
            snapshot = snapshot2;
        }
        return new ViewChange(snapshot, limboDocumentChanges);
    }

    /* JADX INFO: renamed from: lambda$applyChanges$0$com-google-firebase-firestore-core-View, reason: not valid java name */
    /* synthetic */ int m8978lambda$applyChanges$0$comgooglefirebasefirestorecoreView(DocumentViewChange o1, DocumentViewChange o2) {
        int typeComp = Util.compareIntegers(changeTypeOrder(o1), changeTypeOrder(o2));
        if (typeComp != 0) {
            return typeComp;
        }
        return this.query.comparator().compare(o1.getDocument(), o2.getDocument());
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (this.current && onlineState == OnlineState.OFFLINE) {
            this.current = false;
            return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false));
        }
        return new ViewChange(null, Collections.emptyList());
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            for (DocumentKey documentKey : targetChange.getAddedDocuments()) {
                this.syncedDocuments = this.syncedDocuments.insert(documentKey);
            }
            for (DocumentKey documentKey2 : targetChange.getModifiedDocuments()) {
                Assert.hardAssert(this.syncedDocuments.contains(documentKey2), "Modified document %s not found in view.", documentKey2);
            }
            Iterator<DocumentKey> it = targetChange.getRemovedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> oldLimboDocs = this.limboDocuments;
        this.limboDocuments = DocumentKey.emptyKeySet();
        for (Document doc : this.documentSet) {
            if (shouldBeLimboDoc(doc.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(doc.getKey());
            }
        }
        List<LimboDocumentChange> changes = new ArrayList<>(oldLimboDocs.size() + this.limboDocuments.size());
        for (DocumentKey key : oldLimboDocs) {
            if (!this.limboDocuments.contains(key)) {
                changes.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, key));
            }
        }
        for (DocumentKey key2 : this.limboDocuments) {
            if (!oldLimboDocs.contains(key2)) {
                changes.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, key2));
            }
        }
        return changes;
    }

    private boolean shouldBeLimboDoc(DocumentKey key) {
        Document doc;
        return (this.syncedDocuments.contains(key) || (doc = this.documentSet.getDocument(key)) == null || doc.hasLocalMutations()) ? false : true;
    }

    ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    private static int changeTypeOrder(DocumentViewChange change) {
        switch (change.getType()) {
            case ADDED:
                return 1;
            case MODIFIED:
                return 2;
            case METADATA:
                return 2;
            case REMOVED:
                return 0;
            default:
                throw new IllegalArgumentException("Unknown change type: " + change.getType());
        }
    }
}
