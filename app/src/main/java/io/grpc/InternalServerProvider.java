package io.grpc;

/* JADX INFO: loaded from: classes13.dex */
public final class InternalServerProvider {
    private InternalServerProvider() {
    }

    public static ServerBuilder<?> builderForPort(ServerProvider provider, int port) {
        return provider.builderForPort(port);
    }

    public static ServerProvider.NewServerBuilderResult newServerBuilderForPort(ServerProvider provider, int port, ServerCredentials creds) {
        return provider.newServerBuilderForPort(port, creds);
    }
}
