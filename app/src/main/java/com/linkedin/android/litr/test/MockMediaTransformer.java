package com.linkedin.android.litr.test;

import android.content.Context;
import android.media.MediaFormat;
import android.net.Uri;
import com.linkedin.android.litr.MediaTransformer;
import com.linkedin.android.litr.TrackTransform;
import com.linkedin.android.litr.TransformationListener;
import com.linkedin.android.litr.codec.Decoder;
import com.linkedin.android.litr.codec.Encoder;
import com.linkedin.android.litr.filter.GlFilter;
import com.linkedin.android.litr.io.MediaSource;
import com.linkedin.android.litr.io.MediaTarget;
import com.linkedin.android.litr.render.Renderer;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public class MockMediaTransformer extends MediaTransformer {
    private List<TransformationEvent> transformationEvents;

    public MockMediaTransformer(Context context) {
        super(context, null, null);
    }

    public void setEvents(List<TransformationEvent> transformationEvents) {
        this.transformationEvents = transformationEvents;
    }

    @Override // com.linkedin.android.litr.MediaTransformer
    public void transform(String requestId, Uri inputUri, String outputFilePath, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat, TransformationListener listener, int granularity, List<GlFilter> filters) {
        playEvents(listener);
    }

    @Override // com.linkedin.android.litr.MediaTransformer
    public void transform(String requestId, MediaSource mediaSource, Decoder decoder, Renderer videoRenderer, Encoder encoder, MediaTarget mediaTarget, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat, TransformationListener listener, int granularity) {
        playEvents(listener);
    }

    @Override // com.linkedin.android.litr.MediaTransformer
    public void transform(String requestId, List<TrackTransform> trackTransforms, TransformationListener listener, int granularity) {
        playEvents(listener);
    }

    @Override // com.linkedin.android.litr.MediaTransformer
    public void cancel(String requestId) {
    }

    @Override // com.linkedin.android.litr.MediaTransformer
    public void release() {
    }

    @Override // com.linkedin.android.litr.MediaTransformer
    public long getEstimatedTargetVideoSize(Uri inputUri, MediaFormat targetVideoFormat, MediaFormat targetAudioFormat) {
        return 0L;
    }

    private void playEvents(TransformationListener transformationListener) {
        if (this.transformationEvents == null) {
            return;
        }
        for (TransformationEvent event : this.transformationEvents) {
            switch (event.type) {
                case 0:
                    transformationListener.onStarted(event.id);
                    break;
                case 1:
                    transformationListener.onProgress(event.id, event.progress);
                    break;
                case 2:
                    transformationListener.onCompleted(event.id, null);
                    break;
                case 3:
                    transformationListener.onError(event.id, event.cause, null);
                    break;
                case 4:
                    transformationListener.onCancelled(event.id, null);
                    break;
            }
        }
    }
}
