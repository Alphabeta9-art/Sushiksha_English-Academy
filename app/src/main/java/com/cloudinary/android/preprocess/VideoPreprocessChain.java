package com.cloudinary.android.preprocess;

import android.net.Uri;

/* JADX INFO: loaded from: classes11.dex */
public class VideoPreprocessChain extends PreprocessChain<Uri> {
    public static VideoPreprocessChain videoTranscodingChain(Parameters parameters) {
        return (VideoPreprocessChain) new VideoPreprocessChain().addStep(new Transcode(parameters));
    }

    @Override // com.cloudinary.android.preprocess.PreprocessChain
    protected ResourceEncoder<Uri> getDefaultEncoder() {
        return new VideoEncoder();
    }

    @Override // com.cloudinary.android.preprocess.PreprocessChain
    protected ResourceDecoder<Uri> getDefaultDecoder() {
        return new VideoDecoder();
    }
}
