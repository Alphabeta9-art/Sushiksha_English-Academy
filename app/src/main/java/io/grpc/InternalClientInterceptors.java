package io.grpc;

/* JADX INFO: loaded from: classes13.dex */
public final class InternalClientInterceptors {
    public static <ReqT, RespT> ClientInterceptor wrapClientInterceptor(ClientInterceptor interceptor, MethodDescriptor.Marshaller<ReqT> reqMarshaller, MethodDescriptor.Marshaller<RespT> respMarshaller) {
        return ClientInterceptors.wrapClientInterceptor(interceptor, reqMarshaller, respMarshaller);
    }
}
