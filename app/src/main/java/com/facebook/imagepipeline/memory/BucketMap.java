package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import java.util.LinkedList;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class BucketMap<T> {

    @Nullable
    LinkedEntry<T> mHead;
    protected final SparseArray<LinkedEntry<T>> mMap = new SparseArray<>();

    @Nullable
    LinkedEntry<T> mTail;

    static class LinkedEntry<I> {
        int key;

        @Nullable
        LinkedEntry<I> next;

        @Nullable
        LinkedEntry<I> prev;
        LinkedList<I> value;

        private LinkedEntry(@Nullable LinkedEntry<I> prev, int key, LinkedList<I> value, @Nullable LinkedEntry<I> next) {
            this.prev = prev;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public String toString() {
            return "LinkedEntry(key: " + this.key + ")";
        }
    }

    @Nullable
    public synchronized T acquire(int key) {
        LinkedEntry<T> bucket = this.mMap.get(key);
        if (bucket == null) {
            return null;
        }
        T result = bucket.value.pollFirst();
        moveToFront(bucket);
        return result;
    }

    public synchronized void release(int key, T value) {
        LinkedEntry<T> bucket = this.mMap.get(key);
        if (bucket == null) {
            bucket = new LinkedEntry<>(null, key, new LinkedList(), null);
            this.mMap.put(key, bucket);
        }
        bucket.value.addLast(value);
        moveToFront(bucket);
    }

    synchronized int valueCount() {
        int count;
        count = 0;
        for (LinkedEntry entry = this.mHead; entry != null; entry = entry.next) {
            if (entry.value != null) {
                count += entry.value.size();
            }
        }
        return count;
    }

    private synchronized void prune(LinkedEntry<T> linkedEntry) {
        LinkedEntry linkedEntry2 = (LinkedEntry<T>) linkedEntry.prev;
        LinkedEntry linkedEntry3 = (LinkedEntry<T>) linkedEntry.next;
        if (linkedEntry2 != null) {
            linkedEntry2.next = linkedEntry3;
        }
        if (linkedEntry3 != null) {
            linkedEntry3.prev = linkedEntry2;
        }
        linkedEntry.prev = null;
        linkedEntry.next = null;
        if (linkedEntry == this.mHead) {
            this.mHead = linkedEntry3;
        }
        if (linkedEntry == this.mTail) {
            this.mTail = linkedEntry2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void moveToFront(LinkedEntry<T> linkedEntry) {
        if (this.mHead == linkedEntry) {
            return;
        }
        prune(linkedEntry);
        if (this.mHead == null) {
            this.mHead = linkedEntry;
            this.mTail = linkedEntry;
        } else {
            linkedEntry.next = this.mHead;
            this.mHead.prev = linkedEntry;
            this.mHead = linkedEntry;
        }
    }

    @Nullable
    public synchronized T removeFromEnd() {
        LinkedEntry<T> last = this.mTail;
        if (last == null) {
            return null;
        }
        T value = last.value.pollLast();
        maybePrune(last);
        return value;
    }

    private void maybePrune(LinkedEntry<T> bucket) {
        if (bucket != null && bucket.value.isEmpty()) {
            prune(bucket);
            this.mMap.remove(bucket.key);
        }
    }
}
