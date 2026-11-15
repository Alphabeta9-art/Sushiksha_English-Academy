package com.google.firestore.v1;

import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes12.dex */
public interface BloomFilterOrBuilder extends MessageLiteOrBuilder {
    BitSequence getBits();

    int getHashCount();

    boolean hasBits();
}
