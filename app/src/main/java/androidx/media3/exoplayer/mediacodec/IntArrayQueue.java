package androidx.media3.exoplayer.mediacodec;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes11.dex */
final class IntArrayQueue {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int headIndex = 0;
    private int tailIndex = -1;
    private int size = 0;
    private int[] data = new int[16];
    private int wrapAroundMask = this.data.length - 1;

    public void add(int value) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        this.tailIndex = (this.tailIndex + 1) & this.wrapAroundMask;
        this.data[this.tailIndex] = value;
        this.size++;
    }

    public int remove() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        int value = this.data[this.headIndex];
        this.headIndex = (this.headIndex + 1) & this.wrapAroundMask;
        this.size--;
        return value;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public int capacity() {
        return this.data.length;
    }

    private void doubleArraySize() {
        int newCapacity = this.data.length << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException();
        }
        int[] newData = new int[newCapacity];
        int itemsToRight = this.data.length - this.headIndex;
        int itemsToLeft = this.headIndex;
        System.arraycopy(this.data, this.headIndex, newData, 0, itemsToRight);
        System.arraycopy(this.data, 0, newData, itemsToRight, itemsToLeft);
        this.headIndex = 0;
        this.tailIndex = this.size - 1;
        this.data = newData;
        this.wrapAroundMask = this.data.length - 1;
    }
}
