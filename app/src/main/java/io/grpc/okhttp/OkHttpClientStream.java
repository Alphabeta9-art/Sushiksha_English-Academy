package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import java.io.EOFException;
import java.util.List;
import okio.Buffer;

/* JADX INFO: loaded from: classes13.dex */
class OkHttpClientStream extends AbstractClientStream {
    public static final int ABSENT_ID = -1;
    private static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    private String authority;
    private final MethodDescriptor<?, ?> method;
    private final Sink sink;
    private final TransportState state;
    private final StatsTraceContext statsTraceCtx;
    private boolean useGet;
    private final String userAgent;

    OkHttpClientStream(MethodDescriptor<?, ?> method, Metadata headers, ExceptionHandlingFrameWriter frameWriter, OkHttpClientTransport transport, OutboundFlowController outboundFlow, Object lock, int maxMessageSize, int initialWindowSize, String authority, String userAgent, StatsTraceContext statsTraceCtx, TransportTracer transportTracer, CallOptions callOptions, boolean useGetForSafeMethods) {
        super(new OkHttpWritableBufferAllocator(), statsTraceCtx, transportTracer, headers, callOptions, useGetForSafeMethods && method.isSafe());
        this.sink = new Sink();
        this.useGet = false;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceCtx, "statsTraceCtx");
        this.method = method;
        this.authority = authority;
        this.userAgent = userAgent;
        this.attributes = transport.getAttributes();
        this.state = new TransportState(maxMessageSize, statsTraceCtx, lock, frameWriter, outboundFlow, transport, initialWindowSize, method.getFullMethodName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractClientStream, io.grpc.internal.AbstractStream
    public TransportState transportState() {
        return this.state;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractClientStream
    public Sink abstractClientStreamSink() {
        return this.sink;
    }

    public MethodDescriptor.MethodType getType() {
        return this.method.getType();
    }

    boolean useGet() {
        return this.useGet;
    }

    @Override // io.grpc.internal.ClientStream
    public void setAuthority(String authority) {
        this.authority = (String) Preconditions.checkNotNull(authority, "authority");
    }

    @Override // io.grpc.internal.ClientStream
    public Attributes getAttributes() {
        return this.attributes;
    }

    class Sink implements AbstractClientStream.Sink {
        Sink() {
        }

        /* JADX WARN: Bottom block not found for handler: all -> 0x006a */
        @Override // io.grpc.internal.AbstractClientStream.Sink
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void writeHeaders(io.grpc.Metadata r5, byte[] r6) {
            /*
                r4 = this;
                java.lang.String r0 = "OkHttpClientStream$Sink.writeHeaders"
                io.perfmark.TaskCloseable r0 = io.perfmark.PerfMark.traceTask(r0)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
                r1.<init>()     // Catch: java.lang.Throwable -> L6a
                java.lang.String r2 = "/"
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L6a
                io.grpc.okhttp.OkHttpClientStream r2 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L6a
                io.grpc.MethodDescriptor r2 = io.grpc.okhttp.OkHttpClientStream.access$000(r2)     // Catch: java.lang.Throwable -> L6a
                java.lang.String r2 = r2.getFullMethodName()     // Catch: java.lang.Throwable -> L6a
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L6a
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L6a
                if (r6 == 0) goto L4b
                io.grpc.okhttp.OkHttpClientStream r2 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L6a
                r3 = 1
                io.grpc.okhttp.OkHttpClientStream.access$102(r2, r3)     // Catch: java.lang.Throwable -> L6a
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6a
                r2.<init>()     // Catch: java.lang.Throwable -> L6a
                java.lang.StringBuilder r2 = r2.append(r1)     // Catch: java.lang.Throwable -> L6a
                java.lang.String r3 = "?"
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L6a
                com.google.common.io.BaseEncoding r3 = com.google.common.io.BaseEncoding.base64()     // Catch: java.lang.Throwable -> L6a
                java.lang.String r3 = r3.encode(r6)     // Catch: java.lang.Throwable -> L6a
                java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L6a
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L6a
                r1 = r2
            L4b:
                io.grpc.okhttp.OkHttpClientStream r2 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L6a
                io.grpc.okhttp.OkHttpClientStream$TransportState r2 = io.grpc.okhttp.OkHttpClientStream.access$200(r2)     // Catch: java.lang.Throwable -> L6a
                java.lang.Object r2 = io.grpc.okhttp.OkHttpClientStream.TransportState.access$300(r2)     // Catch: java.lang.Throwable -> L6a
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L6a
                io.grpc.okhttp.OkHttpClientStream r3 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L68
                io.grpc.okhttp.OkHttpClientStream$TransportState r3 = io.grpc.okhttp.OkHttpClientStream.access$200(r3)     // Catch: java.lang.Throwable -> L68
                io.grpc.okhttp.OkHttpClientStream.TransportState.access$400(r3, r5, r1)     // Catch: java.lang.Throwable -> L68
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L68
                if (r0 == 0) goto L65
                r0.close()
            L65:
                return
            L66:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L68
                throw r3     // Catch: java.lang.Throwable -> L6a
            L68:
                r3 = move-exception
                goto L66
            L6a:
                r1 = move-exception
                if (r0 == 0) goto L75
                r0.close()     // Catch: java.lang.Throwable -> L71
                goto L75
            L71:
                r2 = move-exception
                r1.addSuppressed(r2)
            L75:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientStream.Sink.writeHeaders(io.grpc.Metadata, byte[]):void");
        }

        /* JADX WARN: Bottom block not found for handler: all -> 0x0048 */
        @Override // io.grpc.internal.AbstractClientStream.Sink
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void writeFrame(io.grpc.internal.WritableBuffer r5, boolean r6, boolean r7, int r8) {
            /*
                r4 = this;
                java.lang.String r0 = "OkHttpClientStream$Sink.writeFrame"
                io.perfmark.TaskCloseable r0 = io.perfmark.PerfMark.traceTask(r0)
                if (r5 != 0) goto Ld
                okio.Buffer r1 = io.grpc.okhttp.OkHttpClientStream.access$500()     // Catch: java.lang.Throwable -> L48
                goto L20
            Ld:
                r1 = r5
                io.grpc.okhttp.OkHttpWritableBuffer r1 = (io.grpc.okhttp.OkHttpWritableBuffer) r1     // Catch: java.lang.Throwable -> L48
                okio.Buffer r1 = r1.buffer()     // Catch: java.lang.Throwable -> L48
                long r2 = r1.size()     // Catch: java.lang.Throwable -> L48
                int r2 = (int) r2     // Catch: java.lang.Throwable -> L48
                if (r2 <= 0) goto L20
                io.grpc.okhttp.OkHttpClientStream r3 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L48
                io.grpc.okhttp.OkHttpClientStream.access$600(r3, r2)     // Catch: java.lang.Throwable -> L48
            L20:
                io.grpc.okhttp.OkHttpClientStream r2 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L48
                io.grpc.okhttp.OkHttpClientStream$TransportState r2 = io.grpc.okhttp.OkHttpClientStream.access$200(r2)     // Catch: java.lang.Throwable -> L48
                java.lang.Object r2 = io.grpc.okhttp.OkHttpClientStream.TransportState.access$300(r2)     // Catch: java.lang.Throwable -> L48
                monitor-enter(r2)     // Catch: java.lang.Throwable -> L48
                io.grpc.okhttp.OkHttpClientStream r3 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L46
                io.grpc.okhttp.OkHttpClientStream$TransportState r3 = io.grpc.okhttp.OkHttpClientStream.access$200(r3)     // Catch: java.lang.Throwable -> L46
                io.grpc.okhttp.OkHttpClientStream.TransportState.access$700(r3, r1, r6, r7)     // Catch: java.lang.Throwable -> L46
                io.grpc.okhttp.OkHttpClientStream r3 = io.grpc.okhttp.OkHttpClientStream.this     // Catch: java.lang.Throwable -> L46
                io.grpc.internal.TransportTracer r3 = io.grpc.okhttp.OkHttpClientStream.access$800(r3)     // Catch: java.lang.Throwable -> L46
                r3.reportMessageSent(r8)     // Catch: java.lang.Throwable -> L46
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L46
                if (r0 == 0) goto L43
                r0.close()
            L43:
                return
            L44:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L46
                throw r3     // Catch: java.lang.Throwable -> L48
            L46:
                r3 = move-exception
                goto L44
            L48:
                r1 = move-exception
                if (r0 == 0) goto L53
                r0.close()     // Catch: java.lang.Throwable -> L4f
                goto L53
            L4f:
                r2 = move-exception
                r1.addSuppressed(r2)
            L53:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.OkHttpClientStream.Sink.writeFrame(io.grpc.internal.WritableBuffer, boolean, boolean, int):void");
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public void cancel(Status reason) {
            TaskCloseable ignore = PerfMark.traceTask("OkHttpClientStream$Sink.cancel");
            try {
                synchronized (OkHttpClientStream.this.state.lock) {
                    try {
                        OkHttpClientStream.this.state.cancel(reason, true, null);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (ignore != null) {
                    ignore.close();
                }
            } catch (Throwable th2) {
                if (ignore != null) {
                    try {
                        ignore.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    class TransportState extends Http2ClientStreamTransportState implements OutboundFlowController.Stream {
        private boolean canStart;
        private boolean cancelSent;
        private boolean flushPendingData;
        private final ExceptionHandlingFrameWriter frameWriter;
        private int id;
        private final int initialWindowSize;
        private final Object lock;
        private final OutboundFlowController outboundFlow;
        private OutboundFlowController.StreamState outboundFlowState;
        private Buffer pendingData;
        private boolean pendingDataHasEndOfStream;
        private int processedWindow;
        private List<Header> requestHeaders;
        private final Tag tag;
        private final OkHttpClientTransport transport;
        private int window;

        public TransportState(int maxMessageSize, StatsTraceContext statsTraceCtx, Object lock, ExceptionHandlingFrameWriter frameWriter, OutboundFlowController outboundFlow, OkHttpClientTransport transport, int initialWindowSize, String methodName) {
            super(maxMessageSize, statsTraceCtx, OkHttpClientStream.this.getTransportTracer());
            this.pendingData = new Buffer();
            this.pendingDataHasEndOfStream = false;
            this.flushPendingData = false;
            this.cancelSent = false;
            this.canStart = true;
            this.id = -1;
            this.lock = Preconditions.checkNotNull(lock, "lock");
            this.frameWriter = frameWriter;
            this.outboundFlow = outboundFlow;
            this.transport = transport;
            this.window = initialWindowSize;
            this.processedWindow = initialWindowSize;
            this.initialWindowSize = initialWindowSize;
            this.tag = PerfMark.createTag(methodName);
        }

        public void start(int streamId) {
            Preconditions.checkState(this.id == -1, "the stream has been started with id %s", streamId);
            this.id = streamId;
            this.outboundFlowState = this.outboundFlow.createState(this, streamId);
            OkHttpClientStream.this.state.onStreamAllocated();
            if (this.canStart) {
                this.frameWriter.synStream(OkHttpClientStream.this.useGet, false, this.id, 0, this.requestHeaders);
                OkHttpClientStream.this.statsTraceCtx.clientOutboundHeaders();
                this.requestHeaders = null;
                if (this.pendingData.size() > 0) {
                    this.outboundFlow.data(this.pendingDataHasEndOfStream, this.outboundFlowState, this.pendingData, this.flushPendingData);
                }
                this.canStart = false;
            }
        }

        @Override // io.grpc.internal.AbstractStream.TransportState
        protected void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportLocalStreamStarted();
        }

        @Override // io.grpc.internal.Http2ClientStreamTransportState
        protected void http2ProcessingFailed(Status status, boolean stopDelivery, Metadata trailers) throws EOFException {
            cancel(status, stopDelivery, trailers);
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframeFailed(Throwable cause) throws EOFException {
            http2ProcessingFailed(Status.fromThrowable(cause), true, new Metadata());
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void bytesRead(int processedBytes) {
            this.processedWindow -= processedBytes;
            if (this.processedWindow <= this.initialWindowSize * 0.5f) {
                int delta = this.initialWindowSize - this.processedWindow;
                this.window += delta;
                this.processedWindow += delta;
                this.frameWriter.windowUpdate(id(), delta);
            }
        }

        @Override // io.grpc.internal.Http2ClientStreamTransportState, io.grpc.internal.AbstractClientStream.TransportState, io.grpc.internal.MessageDeframer.Listener
        public void deframerClosed(boolean hasPartialMessage) {
            onEndOfStream();
            super.deframerClosed(hasPartialMessage);
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener.TransportExecutor
        public void runOnTransportThread(Runnable r) {
            synchronized (this.lock) {
                r.run();
            }
        }

        public void transportHeadersReceived(List<Header> headers, boolean endOfStream) {
            if (endOfStream) {
                transportTrailersReceived(Utils.convertTrailers(headers));
            } else {
                transportHeadersReceived(Utils.convertHeaders(headers));
            }
        }

        public void transportDataReceived(Buffer frame, boolean endOfStream, int paddingLen) {
            int length = (int) frame.size();
            this.window -= length + paddingLen;
            this.processedWindow -= paddingLen;
            if (this.window < 0) {
                this.frameWriter.rstStream(id(), ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(id(), Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), ClientStreamListener.RpcProgress.PROCESSED, false, null, null);
            } else {
                super.transportDataReceived(new OkHttpReadableBuffer(frame), endOfStream);
            }
        }

        private void onEndOfStream() {
            if (!isOutboundClosed()) {
                this.transport.finishStream(id(), null, ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.CANCEL, null);
            } else {
                this.transport.finishStream(id(), null, ClientStreamListener.RpcProgress.PROCESSED, false, null, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel(Status reason, boolean stopDelivery, Metadata trailers) throws EOFException {
            if (this.cancelSent) {
                return;
            }
            this.cancelSent = true;
            if (this.canStart) {
                this.transport.removePendingStream(OkHttpClientStream.this);
                this.requestHeaders = null;
                this.pendingData.clear();
                this.canStart = false;
                transportReportStatus(reason, true, trailers != null ? trailers : new Metadata());
                return;
            }
            this.transport.finishStream(id(), reason, ClientStreamListener.RpcProgress.PROCESSED, stopDelivery, ErrorCode.CANCEL, trailers);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendBuffer(Buffer buffer, boolean endOfStream, boolean flush) {
            if (this.cancelSent) {
                return;
            }
            if (this.canStart) {
                int dataSize = (int) buffer.size();
                this.pendingData.write(buffer, dataSize);
                this.pendingDataHasEndOfStream |= endOfStream;
                this.flushPendingData |= flush;
                return;
            }
            Preconditions.checkState(id() != -1, "streamId should be set");
            this.outboundFlow.data(endOfStream, this.outboundFlowState, buffer, flush);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void streamReady(Metadata metadata, String path) {
            this.requestHeaders = Headers.createRequestHeaders(metadata, path, OkHttpClientStream.this.authority, OkHttpClientStream.this.userAgent, OkHttpClientStream.this.useGet, this.transport.isUsingPlaintext());
            this.transport.streamReadyToStart(OkHttpClientStream.this);
        }

        Tag tag() {
            return this.tag;
        }

        int id() {
            return this.id;
        }

        OutboundFlowController.StreamState getOutboundFlowState() {
            OutboundFlowController.StreamState streamState;
            synchronized (this.lock) {
                streamState = this.outboundFlowState;
            }
            return streamState;
        }
    }
}
