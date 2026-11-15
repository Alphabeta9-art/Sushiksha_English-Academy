package androidx.media3.exoplayer.source;

import android.content.Context;
import android.net.Uri;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import androidx.media3.exoplayer.text.SubtitleDecoderFactory;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleExtractor;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes11.dex */
public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private static final String TAG = "DMediaSourceFactory";
    private AdViewProvider adViewProvider;
    private AdsLoader.Provider adsLoaderProvider;
    private DataSource.Factory dataSourceFactory;
    private final DelegateFactoryLoader delegateFactoryLoader;
    private long liveMaxOffsetMs;
    private float liveMaxSpeed;
    private long liveMinOffsetMs;
    private float liveMinSpeed;
    private long liveTargetOffsetMs;
    private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private MediaSource.Factory serverSideAdInsertionMediaSourceFactory;
    private boolean useProgressiveMediaSourceForSubtitles;

    @Deprecated
    public interface AdsLoaderProvider extends AdsLoader.Provider {
    }

    public DefaultMediaSourceFactory(Context context) {
        this(new DefaultDataSource.Factory(context));
    }

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this(new DefaultDataSource.Factory(context), extractorsFactory);
    }

    public DefaultMediaSourceFactory(DataSource.Factory dataSourceFactory) {
        this(dataSourceFactory, new DefaultExtractorsFactory());
    }

    public DefaultMediaSourceFactory(DataSource.Factory dataSourceFactory, ExtractorsFactory extractorsFactory) {
        this.dataSourceFactory = dataSourceFactory;
        this.delegateFactoryLoader = new DelegateFactoryLoader(extractorsFactory);
        this.delegateFactoryLoader.setDataSourceFactory(dataSourceFactory);
        this.liveTargetOffsetMs = C.TIME_UNSET;
        this.liveMinOffsetMs = C.TIME_UNSET;
        this.liveMaxOffsetMs = C.TIME_UNSET;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
    }

    public DefaultMediaSourceFactory experimentalUseProgressiveMediaSourceForSubtitles(boolean useProgressiveMediaSourceForSubtitles) {
        this.useProgressiveMediaSourceForSubtitles = useProgressiveMediaSourceForSubtitles;
        return this;
    }

    @Deprecated
    public DefaultMediaSourceFactory setAdsLoaderProvider(AdsLoader.Provider adsLoaderProvider) {
        this.adsLoaderProvider = adsLoaderProvider;
        return this;
    }

    @Deprecated
    public DefaultMediaSourceFactory setAdViewProvider(AdViewProvider adViewProvider) {
        this.adViewProvider = adViewProvider;
        return this;
    }

    public DefaultMediaSourceFactory setLocalAdInsertionComponents(AdsLoader.Provider adsLoaderProvider, AdViewProvider adViewProvider) {
        this.adsLoaderProvider = (AdsLoader.Provider) Assertions.checkNotNull(adsLoaderProvider);
        this.adViewProvider = (AdViewProvider) Assertions.checkNotNull(adViewProvider);
        return this;
    }

    public DefaultMediaSourceFactory clearLocalAdInsertionComponents() {
        this.adsLoaderProvider = null;
        this.adViewProvider = null;
        return this;
    }

    public DefaultMediaSourceFactory setDataSourceFactory(DataSource.Factory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
        this.delegateFactoryLoader.setDataSourceFactory(dataSourceFactory);
        return this;
    }

    public DefaultMediaSourceFactory setServerSideAdInsertionMediaSourceFactory(MediaSource.Factory serverSideAdInsertionMediaSourceFactory) {
        this.serverSideAdInsertionMediaSourceFactory = serverSideAdInsertionMediaSourceFactory;
        return this;
    }

    public DefaultMediaSourceFactory setLiveTargetOffsetMs(long liveTargetOffsetMs) {
        this.liveTargetOffsetMs = liveTargetOffsetMs;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinOffsetMs(long liveMinOffsetMs) {
        this.liveMinOffsetMs = liveMinOffsetMs;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxOffsetMs(long liveMaxOffsetMs) {
        this.liveMaxOffsetMs = liveMaxOffsetMs;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinSpeed(float minSpeed) {
        this.liveMinSpeed = minSpeed;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxSpeed(float maxSpeed) {
        this.liveMaxSpeed = maxSpeed;
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setCmcdConfigurationFactory(CmcdConfiguration.Factory cmcdConfigurationFactory) {
        this.delegateFactoryLoader.setCmcdConfigurationFactory((CmcdConfiguration.Factory) Assertions.checkNotNull(cmcdConfigurationFactory));
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
        this.delegateFactoryLoader.setDrmSessionManagerProvider((DrmSessionManagerProvider) Assertions.checkNotNull(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior."));
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(loadErrorHandlingPolicy, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
        this.delegateFactoryLoader.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public int[] getSupportedTypes() {
        return this.delegateFactoryLoader.getSupportedTypes();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public MediaSource createMediaSource(MediaItem mediaItem) {
        long j;
        MediaItem mediaItem2 = mediaItem;
        Assertions.checkNotNull(mediaItem2.localConfiguration);
        String scheme = mediaItem2.localConfiguration.uri.getScheme();
        if (scheme != null && scheme.equals(C.SSAI_SCHEME)) {
            return ((MediaSource.Factory) Assertions.checkNotNull(this.serverSideAdInsertionMediaSourceFactory)).createMediaSource(mediaItem2);
        }
        int type = Util.inferContentTypeForUriAndMimeType(mediaItem2.localConfiguration.uri, mediaItem2.localConfiguration.mimeType);
        MediaSource.Factory mediaSourceFactory = this.delegateFactoryLoader.getMediaSourceFactory(type);
        Assertions.checkStateNotNull(mediaSourceFactory, "No suitable media source factory found for content type: " + type);
        MediaItem.LiveConfiguration.Builder liveConfigurationBuilder = mediaItem2.liveConfiguration.buildUpon();
        if (mediaItem2.liveConfiguration.targetOffsetMs == C.TIME_UNSET) {
            liveConfigurationBuilder.setTargetOffsetMs(this.liveTargetOffsetMs);
        }
        if (mediaItem2.liveConfiguration.minPlaybackSpeed == -3.4028235E38f) {
            liveConfigurationBuilder.setMinPlaybackSpeed(this.liveMinSpeed);
        }
        if (mediaItem2.liveConfiguration.maxPlaybackSpeed == -3.4028235E38f) {
            liveConfigurationBuilder.setMaxPlaybackSpeed(this.liveMaxSpeed);
        }
        if (mediaItem2.liveConfiguration.minOffsetMs == C.TIME_UNSET) {
            liveConfigurationBuilder.setMinOffsetMs(this.liveMinOffsetMs);
        }
        if (mediaItem2.liveConfiguration.maxOffsetMs == C.TIME_UNSET) {
            liveConfigurationBuilder.setMaxOffsetMs(this.liveMaxOffsetMs);
        }
        MediaItem.LiveConfiguration liveConfiguration = liveConfigurationBuilder.build();
        if (!liveConfiguration.equals(mediaItem2.liveConfiguration)) {
            mediaItem2 = mediaItem2.buildUpon().setLiveConfiguration(liveConfiguration).build();
        }
        MediaSource mediaSource = mediaSourceFactory.createMediaSource(mediaItem2);
        List<MediaItem.SubtitleConfiguration> subtitleConfigurations = ((MediaItem.LocalConfiguration) Util.castNonNull(mediaItem2.localConfiguration)).subtitleConfigurations;
        if (!subtitleConfigurations.isEmpty()) {
            MediaSource[] mediaSources = new MediaSource[subtitleConfigurations.size() + 1];
            mediaSources[0] = mediaSource;
            for (int i = 0; i < subtitleConfigurations.size(); i++) {
                if (this.useProgressiveMediaSourceForSubtitles) {
                    final Format format = new Format.Builder().setSampleMimeType(subtitleConfigurations.get(i).mimeType).setLanguage(subtitleConfigurations.get(i).language).setSelectionFlags(subtitleConfigurations.get(i).selectionFlags).setRoleFlags(subtitleConfigurations.get(i).roleFlags).setLabel(subtitleConfigurations.get(i).label).setId(subtitleConfigurations.get(i).id).build();
                    ExtractorsFactory extractorsFactory = new ExtractorsFactory() { // from class: androidx.media3.exoplayer.source.DefaultMediaSourceFactory$$ExternalSyntheticLambda0
                        @Override // androidx.media3.extractor.ExtractorsFactory
                        public final Extractor[] createExtractors() {
                            return DefaultMediaSourceFactory.lambda$createMediaSource$0(format);
                        }
                    };
                    ProgressiveMediaSource.Factory progressiveMediaSourceFactory = new ProgressiveMediaSource.Factory(this.dataSourceFactory, extractorsFactory);
                    if (this.loadErrorHandlingPolicy != null) {
                        progressiveMediaSourceFactory.setLoadErrorHandlingPolicy(this.loadErrorHandlingPolicy);
                    }
                    mediaSources[i + 1] = progressiveMediaSourceFactory.createMediaSource(MediaItem.fromUri(subtitleConfigurations.get(i).uri.toString()));
                    j = C.TIME_UNSET;
                } else {
                    SingleSampleMediaSource.Factory singleSampleMediaSourceFactory = new SingleSampleMediaSource.Factory(this.dataSourceFactory);
                    if (this.loadErrorHandlingPolicy != null) {
                        singleSampleMediaSourceFactory.setLoadErrorHandlingPolicy(this.loadErrorHandlingPolicy);
                    }
                    MediaItem.SubtitleConfiguration subtitleConfiguration = subtitleConfigurations.get(i);
                    j = C.TIME_UNSET;
                    mediaSources[i + 1] = singleSampleMediaSourceFactory.createMediaSource(subtitleConfiguration, C.TIME_UNSET);
                }
            }
            mediaSource = new MergingMediaSource(mediaSources);
        }
        return maybeWrapWithAdsMediaSource(mediaItem2, maybeClipMediaSource(mediaItem2, mediaSource));
    }

    static /* synthetic */ Extractor[] lambda$createMediaSource$0(Format format) {
        Extractor unknownSubtitlesExtractor;
        Extractor[] extractorArr = new Extractor[1];
        if (SubtitleDecoderFactory.DEFAULT.supportsFormat(format)) {
            unknownSubtitlesExtractor = new SubtitleExtractor(SubtitleDecoderFactory.DEFAULT.createDecoder(format), format);
        } else {
            unknownSubtitlesExtractor = new UnknownSubtitlesExtractor(format);
        }
        extractorArr[0] = unknownSubtitlesExtractor;
        return extractorArr;
    }

    private static MediaSource maybeClipMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        if (mediaItem.clippingConfiguration.startPositionMs == 0 && mediaItem.clippingConfiguration.endPositionMs == Long.MIN_VALUE && !mediaItem.clippingConfiguration.relativeToDefaultPosition) {
            return mediaSource;
        }
        return new ClippingMediaSource(mediaSource, Util.msToUs(mediaItem.clippingConfiguration.startPositionMs), Util.msToUs(mediaItem.clippingConfiguration.endPositionMs), !mediaItem.clippingConfiguration.startsAtKeyFrame, mediaItem.clippingConfiguration.relativeToLiveWindow, mediaItem.clippingConfiguration.relativeToDefaultPosition);
    }

    private MediaSource maybeWrapWithAdsMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        Object objOf;
        Assertions.checkNotNull(mediaItem.localConfiguration);
        MediaItem.AdsConfiguration adsConfiguration = mediaItem.localConfiguration.adsConfiguration;
        if (adsConfiguration == null) {
            return mediaSource;
        }
        AdsLoader.Provider adsLoaderProvider = this.adsLoaderProvider;
        AdViewProvider adViewProvider = this.adViewProvider;
        if (adsLoaderProvider == null || adViewProvider == null) {
            Log.w(TAG, "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
            return mediaSource;
        }
        AdsLoader adsLoader = adsLoaderProvider.getAdsLoader(adsConfiguration);
        if (adsLoader == null) {
            Log.w(TAG, "Playing media without ads, as no AdsLoader was provided.");
            return mediaSource;
        }
        DataSpec dataSpec = new DataSpec(adsConfiguration.adTagUri);
        if (adsConfiguration.adsId != null) {
            objOf = adsConfiguration.adsId;
        } else {
            objOf = ImmutableList.of((Uri) mediaItem.mediaId, mediaItem.localConfiguration.uri, adsConfiguration.adTagUri);
        }
        return new AdsMediaSource(mediaSource, dataSpec, objOf, this, adsLoader, adViewProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class DelegateFactoryLoader {
        private CmcdConfiguration.Factory cmcdConfigurationFactory;
        private DataSource.Factory dataSourceFactory;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private final ExtractorsFactory extractorsFactory;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private final Map<Integer, Supplier<MediaSource.Factory>> mediaSourceFactorySuppliers = new HashMap();
        private final Set<Integer> supportedTypes = new HashSet();
        private final Map<Integer, MediaSource.Factory> mediaSourceFactories = new HashMap();

        public DelegateFactoryLoader(ExtractorsFactory extractorsFactory) {
            this.extractorsFactory = extractorsFactory;
        }

        public int[] getSupportedTypes() {
            ensureAllSuppliersAreLoaded();
            return Ints.toArray(this.supportedTypes);
        }

        public MediaSource.Factory getMediaSourceFactory(int contentType) {
            MediaSource.Factory mediaSourceFactory = this.mediaSourceFactories.get(Integer.valueOf(contentType));
            if (mediaSourceFactory != null) {
                return mediaSourceFactory;
            }
            Supplier<MediaSource.Factory> mediaSourceFactorySupplier = maybeLoadSupplier(contentType);
            if (mediaSourceFactorySupplier == null) {
                return null;
            }
            MediaSource.Factory mediaSourceFactory2 = mediaSourceFactorySupplier.get();
            if (this.cmcdConfigurationFactory != null) {
                mediaSourceFactory2.setCmcdConfigurationFactory(this.cmcdConfigurationFactory);
            }
            if (this.drmSessionManagerProvider != null) {
                mediaSourceFactory2.setDrmSessionManagerProvider(this.drmSessionManagerProvider);
            }
            if (this.loadErrorHandlingPolicy != null) {
                mediaSourceFactory2.setLoadErrorHandlingPolicy(this.loadErrorHandlingPolicy);
            }
            this.mediaSourceFactories.put(Integer.valueOf(contentType), mediaSourceFactory2);
            return mediaSourceFactory2;
        }

        public void setDataSourceFactory(DataSource.Factory dataSourceFactory) {
            if (dataSourceFactory != this.dataSourceFactory) {
                this.dataSourceFactory = dataSourceFactory;
                this.mediaSourceFactorySuppliers.clear();
                this.mediaSourceFactories.clear();
            }
        }

        public void setCmcdConfigurationFactory(CmcdConfiguration.Factory cmcdConfigurationFactory) {
            this.cmcdConfigurationFactory = cmcdConfigurationFactory;
            for (MediaSource.Factory mediaSourceFactory : this.mediaSourceFactories.values()) {
                mediaSourceFactory.setCmcdConfigurationFactory(cmcdConfigurationFactory);
            }
        }

        public void setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.drmSessionManagerProvider = drmSessionManagerProvider;
            for (MediaSource.Factory mediaSourceFactory : this.mediaSourceFactories.values()) {
                mediaSourceFactory.setDrmSessionManagerProvider(drmSessionManagerProvider);
            }
        }

        public void setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            for (MediaSource.Factory mediaSourceFactory : this.mediaSourceFactories.values()) {
                mediaSourceFactory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            }
        }

        private void ensureAllSuppliersAreLoaded() {
            maybeLoadSupplier(0);
            maybeLoadSupplier(1);
            maybeLoadSupplier(2);
            maybeLoadSupplier(3);
            maybeLoadSupplier(4);
        }

        private Supplier<MediaSource.Factory> maybeLoadSupplier(int contentType) {
            if (this.mediaSourceFactorySuppliers.containsKey(Integer.valueOf(contentType))) {
                return this.mediaSourceFactorySuppliers.get(Integer.valueOf(contentType));
            }
            Supplier<MediaSource.Factory> mediaSourceFactorySupplier = null;
            final DataSource.Factory dataSourceFactory = (DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory);
            try {
                switch (contentType) {
                    case 0:
                        final Class<? extends U> clsAsSubclass = Class.forName("androidx.media3.exoplayer.dash.DashMediaSource$Factory").asSubclass(MediaSource.Factory.class);
                        mediaSourceFactorySupplier = new Supplier() { // from class: androidx.media3.exoplayer.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda0
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return DefaultMediaSourceFactory.newInstance(clsAsSubclass, dataSourceFactory);
                            }
                        };
                        break;
                    case 1:
                        final Class<? extends U> clsAsSubclass2 = Class.forName("androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory").asSubclass(MediaSource.Factory.class);
                        mediaSourceFactorySupplier = new Supplier() { // from class: androidx.media3.exoplayer.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda1
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return DefaultMediaSourceFactory.newInstance(clsAsSubclass2, dataSourceFactory);
                            }
                        };
                        break;
                    case 2:
                        final Class<? extends U> clsAsSubclass3 = Class.forName("androidx.media3.exoplayer.hls.HlsMediaSource$Factory").asSubclass(MediaSource.Factory.class);
                        mediaSourceFactorySupplier = new Supplier() { // from class: androidx.media3.exoplayer.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda2
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return DefaultMediaSourceFactory.newInstance(clsAsSubclass3, dataSourceFactory);
                            }
                        };
                        break;
                    case 3:
                        final Class<? extends U> clsAsSubclass4 = Class.forName("androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory").asSubclass(MediaSource.Factory.class);
                        mediaSourceFactorySupplier = new Supplier() { // from class: androidx.media3.exoplayer.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda3
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return DefaultMediaSourceFactory.newInstance(clsAsSubclass4);
                            }
                        };
                        break;
                    case 4:
                        mediaSourceFactorySupplier = new Supplier() { // from class: androidx.media3.exoplayer.source.DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return this.f$0.m8300x6773ea8f(dataSourceFactory);
                            }
                        };
                        break;
                }
            } catch (ClassNotFoundException e) {
            }
            this.mediaSourceFactorySuppliers.put(Integer.valueOf(contentType), mediaSourceFactorySupplier);
            if (mediaSourceFactorySupplier != null) {
                this.supportedTypes.add(Integer.valueOf(contentType));
            }
            return mediaSourceFactorySupplier;
        }

        /* JADX INFO: renamed from: lambda$maybeLoadSupplier$4$androidx-media3-exoplayer-source-DefaultMediaSourceFactory$DelegateFactoryLoader, reason: not valid java name */
        /* synthetic */ MediaSource.Factory m8300x6773ea8f(DataSource.Factory dataSourceFactory) {
            return new ProgressiveMediaSource.Factory(dataSourceFactory, this.extractorsFactory);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class UnknownSubtitlesExtractor implements Extractor {
        private final Format format;

        public UnknownSubtitlesExtractor(Format format) {
            this.format = format;
        }

        @Override // androidx.media3.extractor.Extractor
        public boolean sniff(ExtractorInput input) {
            return true;
        }

        @Override // androidx.media3.extractor.Extractor
        public void init(ExtractorOutput output) {
            TrackOutput trackOutput = output.track(0, 3);
            output.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
            output.endTracks();
            trackOutput.format(this.format.buildUpon().setSampleMimeType(MimeTypes.TEXT_UNKNOWN).setCodecs(this.format.sampleMimeType).build());
        }

        @Override // androidx.media3.extractor.Extractor
        public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException {
            int skipResult = input.skip(Integer.MAX_VALUE);
            if (skipResult == -1) {
                return -1;
            }
            return 0;
        }

        @Override // androidx.media3.extractor.Extractor
        public void seek(long position, long timeUs) {
        }

        @Override // androidx.media3.extractor.Extractor
        public void release() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> clazz, DataSource.Factory dataSourceFactory) {
        try {
            return clazz.getConstructor(DataSource.Factory.class).newInstance(dataSourceFactory);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> clazz) {
        try {
            return clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
