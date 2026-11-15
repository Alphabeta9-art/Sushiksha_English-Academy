package androidx.media3.exoplayer.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import com.google.common.collect.ImmutableList;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: loaded from: classes11.dex */
public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    private static final int MSG_UPDATE_OUTPUT = 0;
    private static final int REPLACEMENT_STATE_NONE = 0;
    private static final int REPLACEMENT_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REPLACEMENT_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "TextRenderer";
    private SubtitleDecoder decoder;
    private final SubtitleDecoderFactory decoderFactory;
    private int decoderReplacementState;
    private long finalStreamEndPositionUs;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private long lastRendererPositionUs;
    private SubtitleInputBuffer nextInputBuffer;
    private SubtitleOutputBuffer nextSubtitle;
    private int nextSubtitleEventIndex;
    private final TextOutput output;
    private final Handler outputHandler;
    private boolean outputStreamEnded;
    private long outputStreamOffsetUs;
    private Format streamFormat;
    private SubtitleOutputBuffer subtitle;
    private boolean waitingForKeyFrame;

    public TextRenderer(TextOutput output, Looper outputLooper) {
        this(output, outputLooper, SubtitleDecoderFactory.DEFAULT);
    }

    public TextRenderer(TextOutput output, Looper outputLooper, SubtitleDecoderFactory decoderFactory) {
        super(3);
        this.output = (TextOutput) Assertions.checkNotNull(output);
        this.outputHandler = outputLooper == null ? null : Util.createHandler(outputLooper, this);
        this.decoderFactory = decoderFactory;
        this.formatHolder = new FormatHolder();
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.lastRendererPositionUs = C.TIME_UNSET;
    }

    @Override // androidx.media3.exoplayer.Renderer, androidx.media3.exoplayer.RendererCapabilities
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) {
        if (this.decoderFactory.supportsFormat(format)) {
            return RendererCapabilities.create(format.cryptoType == 0 ? 4 : 2);
        }
        if (MimeTypes.isText(format.sampleMimeType)) {
            return RendererCapabilities.create(1);
        }
        return RendererCapabilities.create(0);
    }

    public void setFinalStreamEndPositionUs(long streamEndPositionUs) {
        Assertions.checkState(isCurrentStreamFinal());
        this.finalStreamEndPositionUs = streamEndPositionUs;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onStreamChanged(Format[] formats, long startPositionUs, long offsetUs) {
        this.outputStreamOffsetUs = offsetUs;
        this.streamFormat = formats[0];
        if (this.decoder != null) {
            this.decoderReplacementState = 1;
        } else {
            initDecoder();
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onPositionReset(long positionUs, boolean joining) {
        this.lastRendererPositionUs = positionUs;
        clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        if (this.decoderReplacementState != 0) {
            replaceDecoder();
        } else {
            releaseBuffers();
            ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).flush();
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long positionUs, long elapsedRealtimeUs) {
        this.lastRendererPositionUs = positionUs;
        if (isCurrentStreamFinal() && this.finalStreamEndPositionUs != C.TIME_UNSET && positionUs >= this.finalStreamEndPositionUs) {
            releaseBuffers();
            this.outputStreamEnded = true;
        }
        if (this.outputStreamEnded) {
            return;
        }
        if (this.nextSubtitle == null) {
            ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).setPositionUs(positionUs);
            try {
                this.nextSubtitle = ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).dequeueOutputBuffer();
            } catch (SubtitleDecoderException e) {
                handleDecoderError(e);
                return;
            }
        }
        if (getState() != 2) {
            return;
        }
        boolean textRendererNeedsUpdate = false;
        if (this.subtitle != null) {
            long subtitleNextEventTimeUs = getNextEventTime();
            while (subtitleNextEventTimeUs <= positionUs) {
                this.nextSubtitleEventIndex++;
                subtitleNextEventTimeUs = getNextEventTime();
                textRendererNeedsUpdate = true;
            }
        }
        if (this.nextSubtitle != null) {
            SubtitleOutputBuffer nextSubtitle = this.nextSubtitle;
            if (nextSubtitle.isEndOfStream()) {
                if (!textRendererNeedsUpdate && getNextEventTime() == Long.MAX_VALUE) {
                    if (this.decoderReplacementState == 2) {
                        replaceDecoder();
                    } else {
                        releaseBuffers();
                        this.outputStreamEnded = true;
                    }
                }
            } else if (nextSubtitle.timeUs <= positionUs) {
                if (this.subtitle != null) {
                    this.subtitle.release();
                }
                this.nextSubtitleEventIndex = nextSubtitle.getNextEventTimeIndex(positionUs);
                this.subtitle = nextSubtitle;
                this.nextSubtitle = null;
                textRendererNeedsUpdate = true;
            }
        }
        if (textRendererNeedsUpdate) {
            Assertions.checkNotNull(this.subtitle);
            long presentationTimeUs = getPresentationTimeUs(getCurrentEventTimeUs(positionUs));
            CueGroup cueGroup = new CueGroup(this.subtitle.getCues(positionUs), presentationTimeUs);
            updateOutput(cueGroup);
        }
        if (this.decoderReplacementState == 2) {
            return;
        }
        while (!this.inputStreamEnded) {
            try {
                SubtitleInputBuffer nextInputBuffer = this.nextInputBuffer;
                if (nextInputBuffer == null) {
                    nextInputBuffer = ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).dequeueInputBuffer();
                    if (nextInputBuffer == null) {
                        return;
                    } else {
                        this.nextInputBuffer = nextInputBuffer;
                    }
                }
                if (this.decoderReplacementState == 1) {
                    nextInputBuffer.setFlags(4);
                    ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(nextInputBuffer);
                    this.nextInputBuffer = null;
                    this.decoderReplacementState = 2;
                    return;
                }
                int result = readSource(this.formatHolder, nextInputBuffer, 0);
                if (result == -4) {
                    if (nextInputBuffer.isEndOfStream()) {
                        this.inputStreamEnded = true;
                        this.waitingForKeyFrame = false;
                    } else {
                        Format format = this.formatHolder.format;
                        if (format == null) {
                            return;
                        }
                        nextInputBuffer.subsampleOffsetUs = format.subsampleOffsetUs;
                        nextInputBuffer.flip();
                        this.waitingForKeyFrame &= nextInputBuffer.isKeyFrame() ? false : true;
                    }
                    if (!this.waitingForKeyFrame) {
                        ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).queueInputBuffer(nextInputBuffer);
                        this.nextInputBuffer = null;
                    }
                } else if (result == -3) {
                    return;
                }
            } catch (SubtitleDecoderException e2) {
                handleDecoderError(e2);
                return;
            }
        }
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    protected void onDisabled() {
        this.streamFormat = null;
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        clearOutput();
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.lastRendererPositionUs = C.TIME_UNSET;
        releaseDecoder();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        return true;
    }

    private void releaseBuffers() {
        this.nextInputBuffer = null;
        this.nextSubtitleEventIndex = -1;
        if (this.subtitle != null) {
            this.subtitle.release();
            this.subtitle = null;
        }
        if (this.nextSubtitle != null) {
            this.nextSubtitle.release();
            this.nextSubtitle = null;
        }
    }

    private void releaseDecoder() {
        releaseBuffers();
        ((SubtitleDecoder) Assertions.checkNotNull(this.decoder)).release();
        this.decoder = null;
        this.decoderReplacementState = 0;
    }

    private void initDecoder() {
        this.waitingForKeyFrame = true;
        this.decoder = this.decoderFactory.createDecoder((Format) Assertions.checkNotNull(this.streamFormat));
    }

    private void replaceDecoder() {
        releaseDecoder();
        initDecoder();
    }

    private long getNextEventTime() {
        if (this.nextSubtitleEventIndex == -1) {
            return Long.MAX_VALUE;
        }
        Assertions.checkNotNull(this.subtitle);
        if (this.nextSubtitleEventIndex >= this.subtitle.getEventTimeCount()) {
            return Long.MAX_VALUE;
        }
        return this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    private void updateOutput(CueGroup cueGroup) {
        if (this.outputHandler != null) {
            this.outputHandler.obtainMessage(0, cueGroup).sendToTarget();
        } else {
            invokeUpdateOutputInternal(cueGroup);
        }
    }

    private void clearOutput() {
        updateOutput(new CueGroup(ImmutableList.of(), getPresentationTimeUs(this.lastRendererPositionUs)));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                invokeUpdateOutputInternal((CueGroup) msg.obj);
                return true;
            default:
                throw new IllegalStateException();
        }
    }

    private void invokeUpdateOutputInternal(CueGroup cueGroup) {
        this.output.onCues(cueGroup.cues);
        this.output.onCues(cueGroup);
    }

    private void handleDecoderError(SubtitleDecoderException e) {
        Log.e(TAG, "Subtitle decoding failed. streamFormat=" + this.streamFormat, e);
        clearOutput();
        replaceDecoder();
    }

    @RequiresNonNull({"subtitle"})
    @SideEffectFree
    private long getCurrentEventTimeUs(long positionUs) {
        int nextEventTimeIndex = this.subtitle.getNextEventTimeIndex(positionUs);
        if (nextEventTimeIndex == 0 || this.subtitle.getEventTimeCount() == 0) {
            return this.subtitle.timeUs;
        }
        if (nextEventTimeIndex == -1) {
            return this.subtitle.getEventTime(this.subtitle.getEventTimeCount() - 1);
        }
        return this.subtitle.getEventTime(nextEventTimeIndex - 1);
    }

    @SideEffectFree
    private long getPresentationTimeUs(long positionUs) {
        Assertions.checkState(positionUs != C.TIME_UNSET);
        Assertions.checkState(this.outputStreamOffsetUs != C.TIME_UNSET);
        return positionUs - this.outputStreamOffsetUs;
    }
}
