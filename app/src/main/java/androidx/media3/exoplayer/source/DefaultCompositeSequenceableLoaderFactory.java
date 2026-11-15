package androidx.media3.exoplayer.source;

/* JADX INFO: loaded from: classes11.dex */
public final class DefaultCompositeSequenceableLoaderFactory implements CompositeSequenceableLoaderFactory {
    @Override // androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory
    public SequenceableLoader createCompositeSequenceableLoader(SequenceableLoader... loaders) {
        return new CompositeSequenceableLoader(loaders);
    }
}
