package com.cloudinary.android;

import com.cloudinary.android.payload.Payload;

/* JADX INFO: loaded from: classes11.dex */
public class UploadContext<T extends Payload> {
    private final RequestDispatcher dispatcher;
    private final T payload;

    public UploadContext(T payload, RequestDispatcher requestDispatcher) {
        this.payload = payload;
        this.dispatcher = requestDispatcher;
    }

    public T getPayload() {
        return this.payload;
    }

    RequestDispatcher getDispatcher() {
        return this.dispatcher;
    }
}
