package androidx.media3.extractor;

import android.net.Uri;
import androidx.media3.common.FileTypes;
import androidx.media3.common.Format;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.amr.AmrExtractor;
import androidx.media3.extractor.avi.AviExtractor;
import androidx.media3.extractor.flac.FlacExtractor;
import androidx.media3.extractor.flv.FlvExtractor;
import androidx.media3.extractor.jpeg.JpegExtractor;
import androidx.media3.extractor.mkv.MatroskaExtractor;
import androidx.media3.extractor.mp3.Mp3Extractor;
import androidx.media3.extractor.mp4.FragmentedMp4Extractor;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.ogg.OggExtractor;
import androidx.media3.extractor.ts.Ac3Extractor;
import androidx.media3.extractor.ts.Ac4Extractor;
import androidx.media3.extractor.ts.AdtsExtractor;
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.extractor.wav.WavExtractor;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes11.dex */
public final class DefaultExtractorsFactory implements ExtractorsFactory {
    private static final int[] DEFAULT_EXTRACTOR_ORDER = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14};
    private static final ExtensionLoader FLAC_EXTENSION_LOADER = new ExtensionLoader(new ExtensionLoader.ConstructorSupplier() { // from class: androidx.media3.extractor.DefaultExtractorsFactory$$ExternalSyntheticLambda0
        @Override // androidx.media3.extractor.DefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier
        public final Constructor getConstructor() {
            return DefaultExtractorsFactory.getFlacExtractorConstructor();
        }
    });
    private static final ExtensionLoader MIDI_EXTENSION_LOADER = new ExtensionLoader(new ExtensionLoader.ConstructorSupplier() { // from class: androidx.media3.extractor.DefaultExtractorsFactory$$ExternalSyntheticLambda1
        @Override // androidx.media3.extractor.DefaultExtractorsFactory.ExtensionLoader.ConstructorSupplier
        public final Constructor getConstructor() {
            return DefaultExtractorsFactory.getMidiExtractorConstructor();
        }
    });
    private int adtsFlags;
    private int amrFlags;
    private boolean constantBitrateSeekingAlwaysEnabled;
    private boolean constantBitrateSeekingEnabled;
    private int flacFlags;
    private int fragmentedMp4Flags;
    private int matroskaFlags;
    private int mp3Flags;
    private int mp4Flags;
    private int tsFlags;
    private ImmutableList<Format> tsSubtitleFormats;
    private int tsMode = 1;
    private int tsTimestampSearchBytes = TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES;

    public synchronized DefaultExtractorsFactory setConstantBitrateSeekingEnabled(boolean constantBitrateSeekingEnabled) {
        this.constantBitrateSeekingEnabled = constantBitrateSeekingEnabled;
        return this;
    }

    public synchronized DefaultExtractorsFactory setConstantBitrateSeekingAlwaysEnabled(boolean constantBitrateSeekingAlwaysEnabled) {
        this.constantBitrateSeekingAlwaysEnabled = constantBitrateSeekingAlwaysEnabled;
        return this;
    }

    public synchronized DefaultExtractorsFactory setAdtsExtractorFlags(int flags) {
        this.adtsFlags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setAmrExtractorFlags(int flags) {
        this.amrFlags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFlacExtractorFlags(int flags) {
        this.flacFlags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMatroskaExtractorFlags(int flags) {
        this.matroskaFlags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp4ExtractorFlags(int flags) {
        this.mp4Flags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setFragmentedMp4ExtractorFlags(int flags) {
        this.fragmentedMp4Flags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setMp3ExtractorFlags(int flags) {
        this.mp3Flags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorMode(int mode) {
        this.tsMode = mode;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorFlags(int flags) {
        this.tsFlags = flags;
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsSubtitleFormats(List<Format> subtitleFormats) {
        this.tsSubtitleFormats = ImmutableList.copyOf((Collection) subtitleFormats);
        return this;
    }

    public synchronized DefaultExtractorsFactory setTsExtractorTimestampSearchBytes(int timestampSearchBytes) {
        this.tsTimestampSearchBytes = timestampSearchBytes;
        return this;
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors() {
        return createExtractors(Uri.EMPTY, new HashMap());
    }

    @Override // androidx.media3.extractor.ExtractorsFactory
    public synchronized Extractor[] createExtractors(Uri uri, Map<String, List<String>> responseHeaders) {
        List<Extractor> extractors;
        extractors = new ArrayList<>(DEFAULT_EXTRACTOR_ORDER.length);
        int responseHeadersInferredFileType = FileTypes.inferFileTypeFromResponseHeaders(responseHeaders);
        if (responseHeadersInferredFileType != -1) {
            addExtractorsForFileType(responseHeadersInferredFileType, extractors);
        }
        int uriInferredFileType = FileTypes.inferFileTypeFromUri(uri);
        if (uriInferredFileType != -1 && uriInferredFileType != responseHeadersInferredFileType) {
            addExtractorsForFileType(uriInferredFileType, extractors);
        }
        for (int fileType : DEFAULT_EXTRACTOR_ORDER) {
            if (fileType != responseHeadersInferredFileType && fileType != uriInferredFileType) {
                addExtractorsForFileType(fileType, extractors);
            }
        }
        return (Extractor[]) extractors.toArray(new Extractor[extractors.size()]);
    }

    private void addExtractorsForFileType(int fileType, List<Extractor> extractors) {
        int i = 2;
        switch (fileType) {
            case 0:
                extractors.add(new Ac3Extractor());
                break;
            case 1:
                extractors.add(new Ac4Extractor());
                break;
            case 2:
                int i2 = this.adtsFlags | (this.constantBitrateSeekingEnabled ? 1 : 0);
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i = 0;
                }
                extractors.add(new AdtsExtractor(i2 | i));
                break;
            case 3:
                int i3 = this.amrFlags | (this.constantBitrateSeekingEnabled ? 1 : 0);
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i = 0;
                }
                extractors.add(new AmrExtractor(i3 | i));
                break;
            case 4:
                Extractor flacExtractor = FLAC_EXTENSION_LOADER.getExtractor(Integer.valueOf(this.flacFlags));
                if (flacExtractor != null) {
                    extractors.add(flacExtractor);
                } else {
                    extractors.add(new FlacExtractor(this.flacFlags));
                }
                break;
            case 5:
                extractors.add(new FlvExtractor());
                break;
            case 6:
                extractors.add(new MatroskaExtractor(this.matroskaFlags));
                break;
            case 7:
                int i4 = this.mp3Flags | (this.constantBitrateSeekingEnabled ? 1 : 0);
                if (!this.constantBitrateSeekingAlwaysEnabled) {
                    i = 0;
                }
                extractors.add(new Mp3Extractor(i4 | i));
                break;
            case 8:
                extractors.add(new FragmentedMp4Extractor(this.fragmentedMp4Flags));
                extractors.add(new Mp4Extractor(this.mp4Flags));
                break;
            case 9:
                extractors.add(new OggExtractor());
                break;
            case 10:
                extractors.add(new PsExtractor());
                break;
            case 11:
                if (this.tsSubtitleFormats == null) {
                    this.tsSubtitleFormats = ImmutableList.of();
                }
                extractors.add(new TsExtractor(this.tsMode, new TimestampAdjuster(0L), new DefaultTsPayloadReaderFactory(this.tsFlags, this.tsSubtitleFormats), this.tsTimestampSearchBytes));
                break;
            case 12:
                extractors.add(new WavExtractor());
                break;
            case 14:
                extractors.add(new JpegExtractor());
                break;
            case 15:
                Extractor midiExtractor = MIDI_EXTENSION_LOADER.getExtractor(new Object[0]);
                if (midiExtractor != null) {
                    extractors.add(midiExtractor);
                }
                break;
            case 16:
                extractors.add(new AviExtractor());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Constructor<? extends Extractor> getMidiExtractorConstructor() throws NoSuchMethodException, ClassNotFoundException {
        return Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(Extractor.class).getConstructor(new Class[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Constructor<? extends Extractor> getFlacExtractorConstructor() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        boolean isFlacNativeLibraryAvailable = Boolean.TRUE.equals(Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", new Class[0]).invoke(null, new Object[0]));
        if (!isFlacNativeLibraryAvailable) {
            return null;
        }
        return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(Extractor.class).getConstructor(Integer.TYPE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ExtensionLoader {
        private final ConstructorSupplier constructorSupplier;
        private final AtomicBoolean extensionLoaded = new AtomicBoolean(false);
        private Constructor<? extends Extractor> extractorConstructor;

        public interface ConstructorSupplier {
            Constructor<? extends Extractor> getConstructor() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException;
        }

        public ExtensionLoader(ConstructorSupplier constructorSupplier) {
            this.constructorSupplier = constructorSupplier;
        }

        public Extractor getExtractor(Object... constructorParams) {
            Constructor<? extends Extractor> extractorConstructor = maybeLoadExtractorConstructor();
            if (extractorConstructor == null) {
                return null;
            }
            try {
                return extractorConstructor.newInstance(constructorParams);
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating extractor", e);
            }
        }

        private Constructor<? extends Extractor> maybeLoadExtractorConstructor() {
            synchronized (this.extensionLoaded) {
                if (this.extensionLoaded.get()) {
                    return this.extractorConstructor;
                }
                try {
                    return this.constructorSupplier.getConstructor();
                } catch (ClassNotFoundException e) {
                    this.extensionLoaded.set(true);
                    return this.extractorConstructor;
                } catch (Exception e2) {
                    throw new RuntimeException("Error instantiating extension", e2);
                }
            }
        }
    }
}
