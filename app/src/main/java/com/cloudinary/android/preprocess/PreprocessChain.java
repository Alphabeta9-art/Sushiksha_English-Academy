package com.cloudinary.android.preprocess;

import android.content.Context;
import com.cloudinary.android.payload.Payload;
import com.cloudinary.android.payload.PayloadNotFoundException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public abstract class PreprocessChain<T> {
    private ResourceDecoder<T> decoder;
    private ResourceEncoder<T> encoder;
    private List<Preprocess<T>> preprocessList = new ArrayList();

    protected abstract ResourceDecoder<T> getDefaultDecoder();

    protected abstract ResourceEncoder<T> getDefaultEncoder();

    public PreprocessChain<T> loadWith(ResourceDecoder<T> decoder) {
        this.decoder = decoder;
        return this;
    }

    public PreprocessChain<T> saveWith(ResourceEncoder<T> encoder) {
        this.encoder = encoder;
        return this;
    }

    public PreprocessChain<T> addStep(Preprocess<T> step) {
        this.preprocessList.add(step);
        return this;
    }

    public String execute(Context context, Payload payload) throws PayloadNotFoundException, PreprocessException {
        ensureDecoderAndEncoder();
        T resource = this.decoder.decode(context, payload);
        for (Preprocess<T> preprocess : this.preprocessList) {
            resource = preprocess.execute(context, resource);
        }
        return this.encoder.encode(context, resource);
    }

    private void ensureDecoderAndEncoder() {
        if (this.encoder == null) {
            this.encoder = getDefaultEncoder();
        }
        if (this.decoder == null) {
            this.decoder = getDefaultDecoder();
        }
    }

    public boolean isEmpty() {
        return this.encoder == null && this.decoder == null && this.preprocessList.isEmpty();
    }
}
