package com.google.protobuf;

/* JADX INFO: loaded from: classes12.dex */
@CheckReturnValue
interface MessageInfoFactory {
    boolean isSupported(Class<?> clazz);

    MessageInfo messageInfoFor(Class<?> clazz);
}
