package com.cloudinary.android.payload;

import android.content.Context;

/* JADX INFO: loaded from: classes11.dex */
public abstract class Payload<T> {
    protected T data;

    public abstract long getLength(Context context) throws PayloadNotFoundException;

    abstract void loadData(String str);

    public abstract Object prepare(Context context) throws PayloadNotFoundException;

    public abstract String toUri();

    public Payload(T data) {
        this.data = data;
    }

    public Payload() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payload<?> payload = (Payload) o;
        if (this.data != null) {
            return this.data.equals(payload.data);
        }
        if (payload.data == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.data != null) {
            return this.data.hashCode();
        }
        return 0;
    }

    public T getData() {
        return this.data;
    }
}
