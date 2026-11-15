package androidx.media3.exoplayer.source;

/* JADX INFO: loaded from: classes11.dex */
public class CompositeSequenceableLoader implements SequenceableLoader {
    protected final SequenceableLoader[] loaders;

    public CompositeSequenceableLoader(SequenceableLoader[] loaders) {
        this.loaders = loaders;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public final long getBufferedPositionUs() {
        long bufferedPositionUs = Long.MAX_VALUE;
        for (SequenceableLoader loader : this.loaders) {
            long loaderBufferedPositionUs = loader.getBufferedPositionUs();
            if (loaderBufferedPositionUs != Long.MIN_VALUE) {
                bufferedPositionUs = Math.min(bufferedPositionUs, loaderBufferedPositionUs);
            }
        }
        if (bufferedPositionUs != Long.MAX_VALUE) {
            return bufferedPositionUs;
        }
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public final long getNextLoadPositionUs() {
        long nextLoadPositionUs = Long.MAX_VALUE;
        for (SequenceableLoader loader : this.loaders) {
            long loaderNextLoadPositionUs = loader.getNextLoadPositionUs();
            if (loaderNextLoadPositionUs != Long.MIN_VALUE) {
                nextLoadPositionUs = Math.min(nextLoadPositionUs, loaderNextLoadPositionUs);
            }
        }
        if (nextLoadPositionUs != Long.MAX_VALUE) {
            return nextLoadPositionUs;
        }
        return Long.MIN_VALUE;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public final void reevaluateBuffer(long positionUs) {
        for (SequenceableLoader loader : this.loaders) {
            loader.reevaluateBuffer(positionUs);
        }
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(long positionUs) {
        boolean madeProgressThisIteration;
        boolean madeProgress = false;
        do {
            madeProgressThisIteration = false;
            long nextLoadPositionUs = getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                break;
            }
            for (SequenceableLoader loader : this.loaders) {
                long loaderNextLoadPositionUs = loader.getNextLoadPositionUs();
                boolean isLoaderBehind = loaderNextLoadPositionUs != Long.MIN_VALUE && loaderNextLoadPositionUs <= positionUs;
                if (loaderNextLoadPositionUs == nextLoadPositionUs || isLoaderBehind) {
                    madeProgressThisIteration |= loader.continueLoading(positionUs);
                }
            }
            madeProgress |= madeProgressThisIteration;
        } while (madeProgressThisIteration);
        return madeProgress;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        for (SequenceableLoader loader : this.loaders) {
            if (loader.isLoading()) {
                return true;
            }
        }
        return false;
    }
}
