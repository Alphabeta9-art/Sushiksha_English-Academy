package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: loaded from: classes11.dex */
public final class Downsampler {
    static final String TAG = "Downsampler";
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final List<ImageHeaderParser> parsers;
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final Option<PreferredColorSpace> PREFERRED_COLOR_SPACE = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.PreferredColorSpace");

    @Deprecated
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = DownsampleStrategy.OPTION;
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", false);
    private static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
    private static final String ICO_MIME_TYPE = "image/x-ico";
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(WBMP_MIME_TYPE, ICO_MIME_TYPE)));
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() { // from class: com.bumptech.glide.load.resource.bitmap.Downsampler.1
        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap downsampled) {
        }
    };
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);

    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    public Downsampler(List<ImageHeaderParser> parsers, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool byteArrayPool) {
        this.parsers = parsers;
        this.displayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics);
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool);
        this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(byteArrayPool);
    }

    public boolean handles(InputStream is) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public boolean handles(ParcelFileDescriptor source) {
        return ParcelFileDescriptorRewinder.isSupported();
    }

    public Resource<Bitmap> decode(InputStream is, int outWidth, int outHeight, Options options) throws IOException {
        return decode(is, outWidth, outHeight, options, EMPTY_CALLBACKS);
    }

    public Resource<Bitmap> decode(ByteBuffer buffer, int requestedWidth, int requestedHeight, Options options) throws IOException {
        return decode(new ImageReader.ByteBufferReader(buffer, this.parsers, this.byteArrayPool), requestedWidth, requestedHeight, options, EMPTY_CALLBACKS);
    }

    public Resource<Bitmap> decode(InputStream is, int requestedWidth, int requestedHeight, Options options, DecodeCallbacks callbacks) throws IOException {
        return decode(new ImageReader.InputStreamImageReader(is, this.parsers, this.byteArrayPool), requestedWidth, requestedHeight, options, callbacks);
    }

    void decode(byte[] bytes, int requestedWidth, int requestedHeight, Options options) throws IOException {
        decode(new ImageReader.ByteArrayReader(bytes, this.parsers, this.byteArrayPool), requestedWidth, requestedHeight, options, EMPTY_CALLBACKS);
    }

    void decode(File file, int requestedWidth, int requestedHeight, Options options) throws IOException {
        decode(new ImageReader.FileReader(file, this.parsers, this.byteArrayPool), requestedWidth, requestedHeight, options, EMPTY_CALLBACKS);
    }

    public Resource<Bitmap> decode(ParcelFileDescriptor parcelFileDescriptor, int outWidth, int outHeight, Options options) throws IOException {
        return decode(new ImageReader.ParcelFileDescriptorImageReader(parcelFileDescriptor, this.parsers, this.byteArrayPool), outWidth, outHeight, options, EMPTY_CALLBACKS);
    }

    private Resource<Bitmap> decode(ImageReader imageReader, int requestedWidth, int requestedHeight, Options options, DecodeCallbacks callbacks) throws IOException {
        byte[] bytesForOptions = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options bitmapFactoryOptions = getDefaultOptions();
        bitmapFactoryOptions.inTempStorage = bytesForOptions;
        DecodeFormat decodeFormat = (DecodeFormat) options.get(DECODE_FORMAT);
        PreferredColorSpace preferredColorSpace = (PreferredColorSpace) options.get(PREFERRED_COLOR_SPACE);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        boolean fixBitmapToRequestedDimensions = ((Boolean) options.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue();
        boolean isHardwareConfigAllowed = options.get(ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options.get(ALLOW_HARDWARE_CONFIG)).booleanValue();
        try {
            Bitmap result = decodeFromWrappedStreams(imageReader, bitmapFactoryOptions, downsampleStrategy, decodeFormat, preferredColorSpace, isHardwareConfigAllowed, requestedWidth, requestedHeight, fixBitmapToRequestedDimensions, callbacks);
            return BitmapResource.obtain(result, this.bitmapPool);
        } finally {
            releaseOptions(bitmapFactoryOptions);
            this.byteArrayPool.put(bytesForOptions);
        }
    }

    private Bitmap decodeFromWrappedStreams(ImageReader imageReader, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, PreferredColorSpace preferredColorSpace, boolean isHardwareConfigAllowed, int requestedWidth, int requestedHeight, boolean fixBitmapToRequestedDimensions, DecodeCallbacks callbacks) throws IOException {
        int targetWidth;
        int targetHeight;
        String sourceMimeType;
        int expectedWidth;
        int expectedHeight;
        Bitmap downsampled;
        long startTime = LogTime.getLogTime();
        int[] sourceDimensions = getDimensions(imageReader, options, callbacks, this.bitmapPool);
        boolean isP3Eligible = false;
        int sourceWidth = sourceDimensions[0];
        int sourceHeight = sourceDimensions[1];
        String sourceMimeType2 = options.outMimeType;
        boolean isHardwareConfigAllowed2 = (sourceWidth == -1 || sourceHeight == -1) ? false : isHardwareConfigAllowed;
        int orientation = imageReader.getImageOrientation();
        int degreesToRotate = TransformationUtils.getExifOrientationDegrees(orientation);
        boolean isExifOrientationRequired = TransformationUtils.isExifOrientationRequired(orientation);
        if (requestedWidth == Integer.MIN_VALUE) {
            targetWidth = isRotationRequired(degreesToRotate) ? sourceHeight : sourceWidth;
        } else {
            targetWidth = requestedWidth;
        }
        if (requestedHeight == Integer.MIN_VALUE) {
            targetHeight = isRotationRequired(degreesToRotate) ? sourceWidth : sourceHeight;
        } else {
            targetHeight = requestedHeight;
        }
        ImageHeaderParser.ImageType imageType = imageReader.getImageType();
        int orientation2 = targetWidth;
        calculateScaling(imageType, imageReader, callbacks, this.bitmapPool, downsampleStrategy, degreesToRotate, sourceWidth, sourceHeight, orientation2, targetHeight, options);
        int sourceWidth2 = targetHeight;
        calculateConfig(imageReader, decodeFormat, isHardwareConfigAllowed2, isExifOrientationRequired, options, orientation2, sourceWidth2);
        int i = Build.VERSION.SDK_INT;
        if ((options.inSampleSize != 1 && 1 == 0) || !shouldUsePool(imageType)) {
            sourceMimeType = sourceMimeType2;
        } else {
            if (sourceWidth >= 0 && sourceHeight >= 0 && fixBitmapToRequestedDimensions && 1 != 0) {
                expectedWidth = orientation2;
                sourceMimeType = sourceMimeType2;
            } else {
                float densityMultiplier = isScaling(options) ? options.inTargetDensity / options.inDensity : 1.0f;
                int sampleSize = options.inSampleSize;
                int downsampledWidth = (int) Math.ceil(sourceWidth / sampleSize);
                int downsampledHeight = (int) Math.ceil(sourceHeight / sampleSize);
                int expectedWidth2 = Math.round(downsampledWidth * densityMultiplier);
                sourceMimeType = sourceMimeType2;
                int expectedHeight2 = Math.round(downsampledHeight * densityMultiplier);
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "Calculated target [" + expectedWidth2 + "x" + expectedHeight2 + "] for source [" + sourceWidth + "x" + sourceHeight + "], sampleSize: " + sampleSize + ", targetDensity: " + options.inTargetDensity + ", density: " + options.inDensity + ", density multiplier: " + densityMultiplier);
                }
                expectedWidth = expectedWidth2;
                expectedHeight = expectedHeight2;
            }
            if (expectedWidth > 0 && expectedHeight > 0) {
                setInBitmap(options, this.bitmapPool, expectedWidth, expectedHeight);
            }
        }
        if (preferredColorSpace != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                if (preferredColorSpace == PreferredColorSpace.DISPLAY_P3 && options.outColorSpace != null && options.outColorSpace.isWideGamut()) {
                    isP3Eligible = true;
                }
                options.inPreferredColorSpace = ColorSpace.get(isP3Eligible ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB);
            } else if (Build.VERSION.SDK_INT >= 26) {
                options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
            }
        }
        Bitmap downsampled2 = decodeStream(imageReader, options, callbacks, this.bitmapPool);
        callbacks.onDecodeComplete(this.bitmapPool, downsampled2);
        if (!Log.isLoggable(TAG, 2)) {
            downsampled = downsampled2;
        } else {
            downsampled = downsampled2;
            logDecode(sourceWidth, sourceHeight, sourceMimeType, options, downsampled, requestedWidth, requestedHeight, startTime);
        }
        Bitmap rotated = null;
        if (downsampled != null) {
            downsampled.setDensity(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x01e6: INVOKE 
                  (r4v5 'downsampled' android.graphics.Bitmap A[D('downsampled' android.graphics.Bitmap)])
                  (wrap int:0x01e4: IGET 
                  (wrap android.util.DisplayMetrics:0x01e2: IGET (r0v0 'this' ?? I:??[OBJECT, ARRAY] A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:448) com.bumptech.glide.load.resource.bitmap.Downsampler.displayMetrics android.util.DisplayMetrics)
                 A[WRAPPED] (LINE:448) android.util.DisplayMetrics.densityDpi int)
                 VIRTUAL call: android.graphics.Bitmap.setDensity(int):void A[MD:(int):void (c)] (LINE:448) in method: com.bumptech.glide.load.resource.bitmap.Downsampler.decodeFromWrappedStreams(com.bumptech.glide.load.resource.bitmap.ImageReader, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.DecodeFormat, com.bumptech.glide.load.PreferredColorSpace, boolean, int, int, boolean, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks):android.graphics.Bitmap, file: classes11.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v0 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 507
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.decodeFromWrappedStreams(com.bumptech.glide.load.resource.bitmap.ImageReader, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.DecodeFormat, com.bumptech.glide.load.PreferredColorSpace, boolean, int, int, boolean, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks):android.graphics.Bitmap");
        }

        private static void calculateScaling(ImageHeaderParser.ImageType imageType, ImageReader imageReader, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool, DownsampleStrategy downsampleStrategy, int degreesToRotate, int sourceWidth, int sourceHeight, int targetWidth, int targetHeight, BitmapFactory.Options options) throws IOException {
            int scaleFactor;
            int i;
            int secondaryScaling;
            int powerOfTwoHeight;
            if (sourceWidth <= 0 || sourceHeight <= 0) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Unable to determine dimensions for: " + imageType + " with target [" + targetWidth + "x" + targetHeight + "]");
                    return;
                }
                return;
            }
            int orientedSourceWidth = sourceWidth;
            int orientedSourceHeight = sourceHeight;
            if (isRotationRequired(degreesToRotate)) {
                orientedSourceWidth = sourceHeight;
                orientedSourceHeight = sourceWidth;
            }
            float exactScaleFactor = downsampleStrategy.getScaleFactor(orientedSourceWidth, orientedSourceHeight, targetWidth, targetHeight);
            if (exactScaleFactor <= 0.0f) {
                throw new IllegalArgumentException("Cannot scale with factor: " + exactScaleFactor + " from: " + downsampleStrategy + ", source: [" + sourceWidth + "x" + sourceHeight + "], target: [" + targetWidth + "x" + targetHeight + "]");
            }
            DownsampleStrategy.SampleSizeRounding rounding = downsampleStrategy.getSampleSizeRounding(orientedSourceWidth, orientedSourceHeight, targetWidth, targetHeight);
            if (rounding == null) {
                throw new IllegalArgumentException("Cannot round with null rounding");
            }
            int outWidth = round(orientedSourceWidth * exactScaleFactor);
            int outHeight = round(orientedSourceHeight * exactScaleFactor);
            int widthScaleFactor = orientedSourceWidth / outWidth;
            int outWidth2 = orientedSourceHeight / outHeight;
            if (rounding == DownsampleStrategy.SampleSizeRounding.MEMORY) {
                scaleFactor = Math.max(widthScaleFactor, outWidth2);
            } else {
                scaleFactor = Math.min(widthScaleFactor, outWidth2);
            }
            int i2 = Build.VERSION.SDK_INT;
            int heightScaleFactor = Integer.highestOneBit(scaleFactor);
            int powerOfTwoSampleSize = Math.max(1, heightScaleFactor);
            if (rounding == DownsampleStrategy.SampleSizeRounding.MEMORY && powerOfTwoSampleSize < 1.0f / exactScaleFactor) {
                powerOfTwoSampleSize <<= 1;
            }
            options.inSampleSize = powerOfTwoSampleSize;
            if (imageType != ImageHeaderParser.ImageType.JPEG) {
                i = 0;
                if (imageType == ImageHeaderParser.ImageType.PNG || imageType == ImageHeaderParser.ImageType.PNG_A) {
                    int powerOfTwoWidth = (int) Math.floor(orientedSourceWidth / powerOfTwoSampleSize);
                    secondaryScaling = (int) Math.floor(orientedSourceHeight / powerOfTwoSampleSize);
                    powerOfTwoHeight = powerOfTwoWidth;
                } else if (imageType.isWebp()) {
                    int i3 = Build.VERSION.SDK_INT;
                    powerOfTwoHeight = Math.round(orientedSourceWidth / powerOfTwoSampleSize);
                    secondaryScaling = Math.round(orientedSourceHeight / powerOfTwoSampleSize);
                } else {
                    int powerOfTwoWidth2 = orientedSourceWidth % powerOfTwoSampleSize;
                    if (powerOfTwoWidth2 != 0 || orientedSourceHeight % powerOfTwoSampleSize != 0) {
                        int[] dimensions = getDimensions(imageReader, options, decodeCallbacks, bitmapPool);
                        int powerOfTwoWidth3 = dimensions[0];
                        int powerOfTwoHeight2 = dimensions[1];
                        secondaryScaling = powerOfTwoHeight2;
                        powerOfTwoHeight = powerOfTwoWidth3;
                    } else {
                        powerOfTwoHeight = orientedSourceWidth / powerOfTwoSampleSize;
                        secondaryScaling = orientedSourceHeight / powerOfTwoSampleSize;
                    }
                }
            } else {
                int nativeScaling = Math.min(powerOfTwoSampleSize, 8);
                i = 0;
                int powerOfTwoWidth4 = (int) Math.ceil(orientedSourceWidth / nativeScaling);
                int powerOfTwoHeight3 = (int) Math.ceil(orientedSourceHeight / nativeScaling);
                int secondaryScaling2 = powerOfTwoSampleSize / 8;
                if (secondaryScaling2 <= 0) {
                    secondaryScaling = powerOfTwoHeight3;
                    powerOfTwoHeight = powerOfTwoWidth4;
                } else {
                    int powerOfTwoWidth5 = powerOfTwoWidth4 / secondaryScaling2;
                    secondaryScaling = powerOfTwoHeight3 / secondaryScaling2;
                    powerOfTwoHeight = powerOfTwoWidth5;
                }
            }
            double adjustedScaleFactor = downsampleStrategy.getScaleFactor(powerOfTwoHeight, secondaryScaling, targetWidth, targetHeight);
            int i4 = Build.VERSION.SDK_INT;
            options.inTargetDensity = adjustTargetDensityForError(adjustedScaleFactor);
            options.inDensity = getDensityMultiplier(adjustedScaleFactor);
            if (isScaling(options)) {
                options.inScaled = true;
            } else {
                int i5 = i;
                options.inTargetDensity = i5;
                options.inDensity = i5;
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Calculate scaling, source: [" + sourceWidth + "x" + sourceHeight + "], degreesToRotate: " + degreesToRotate + ", target: [" + targetWidth + "x" + targetHeight + "], power of two scaled: [" + powerOfTwoHeight + "x" + secondaryScaling + "], exact scale factor: " + exactScaleFactor + ", power of 2 sample size: " + powerOfTwoSampleSize + ", adjusted scale factor: " + adjustedScaleFactor + ", target density: " + options.inTargetDensity + ", density: " + options.inDensity);
            }
        }

        private static int adjustTargetDensityForError(double adjustedScaleFactor) {
            int densityMultiplier = getDensityMultiplier(adjustedScaleFactor);
            int targetDensity = round(((double) densityMultiplier) * adjustedScaleFactor);
            float scaleFactorWithError = targetDensity / densityMultiplier;
            double difference = adjustedScaleFactor / ((double) scaleFactorWithError);
            return round(((double) targetDensity) * difference);
        }

        private static int getDensityMultiplier(double adjustedScaleFactor) {
            return (int) Math.round((adjustedScaleFactor <= 1.0d ? adjustedScaleFactor : 1.0d / adjustedScaleFactor) * 2.147483647E9d);
        }

        private static int round(double value) {
            return (int) (0.5d + value);
        }

        private boolean shouldUsePool(ImageHeaderParser.ImageType imageType) {
            int i = Build.VERSION.SDK_INT;
            return true;
        }

        private void calculateConfig(ImageReader imageReader, DecodeFormat format, boolean isHardwareConfigAllowed, boolean isExifOrientationRequired, BitmapFactory.Options optionsWithScaling, int targetWidth, int targetHeight) {
            if (this.hardwareConfigState.setHardwareConfigIfAllowed(targetWidth, targetHeight, optionsWithScaling, isHardwareConfigAllowed, isExifOrientationRequired)) {
                return;
            }
            if (format == DecodeFormat.PREFER_ARGB_8888) {
                optionsWithScaling.inPreferredConfig = Bitmap.Config.ARGB_8888;
                return;
            }
            int i = Build.VERSION.SDK_INT;
            boolean hasAlpha = false;
            try {
                hasAlpha = imageReader.getImageType().hasAlpha();
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Cannot determine whether the image has alpha or not from header, format " + format, e);
                }
            }
            optionsWithScaling.inPreferredConfig = hasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            if (optionsWithScaling.inPreferredConfig == Bitmap.Config.RGB_565) {
                optionsWithScaling.inDither = true;
            }
        }

        private static int[] getDimensions(ImageReader imageReader, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
            options.inJustDecodeBounds = true;
            decodeStream(imageReader, options, decodeCallbacks, bitmapPool);
            options.inJustDecodeBounds = false;
            return new int[]{options.outWidth, options.outHeight};
        }

        private static Bitmap decodeStream(ImageReader imageReader, BitmapFactory.Options options, DecodeCallbacks callbacks, BitmapPool bitmapPool) throws IOException {
            if (!options.inJustDecodeBounds) {
                callbacks.onObtainBounds();
                imageReader.stopGrowingBuffers();
            }
            int sourceWidth = options.outWidth;
            int sourceHeight = options.outHeight;
            String outMimeType = options.outMimeType;
            TransformationUtils.getBitmapDrawableLock().lock();
            try {
                try {
                    Bitmap result = imageReader.decodeBitmap(options);
                    TransformationUtils.getBitmapDrawableLock().unlock();
                    return result;
                } catch (IllegalArgumentException e) {
                    IOException bitmapAssertionException = newIoExceptionForInBitmapAssertion(e, sourceWidth, sourceHeight, outMimeType, options);
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Failed to decode with inBitmap, trying again without Bitmap re-use", bitmapAssertionException);
                    }
                    if (options.inBitmap == null) {
                        throw bitmapAssertionException;
                    }
                    try {
                        bitmapPool.put(options.inBitmap);
                        options.inBitmap = null;
                        Bitmap bitmapDecodeStream = decodeStream(imageReader, options, callbacks, bitmapPool);
                        TransformationUtils.getBitmapDrawableLock().unlock();
                        return bitmapDecodeStream;
                    } catch (IOException e2) {
                        throw bitmapAssertionException;
                    }
                }
            } catch (Throwable th) {
                TransformationUtils.getBitmapDrawableLock().unlock();
                throw th;
            }
        }

        private static boolean isScaling(BitmapFactory.Options options) {
            return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
        }

        private static void logDecode(int sourceWidth, int sourceHeight, String outMimeType, BitmapFactory.Options options, Bitmap result, int requestedWidth, int requestedHeight, long startTime) {
            Log.v(TAG, "Decoded " + getBitmapString(result) + " from [" + sourceWidth + "x" + sourceHeight + "] " + outMimeType + " with inBitmap " + getInBitmapString(options) + " for [" + requestedWidth + "x" + requestedHeight + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + LogTime.getElapsedMillis(startTime));
        }

        private static String getInBitmapString(BitmapFactory.Options options) {
            return getBitmapString(options.inBitmap);
        }

        private static String getBitmapString(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            String sizeString = " (" + bitmap.getAllocationByteCount() + ")";
            return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + sizeString;
        }

        private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException e, int outWidth, int outHeight, String outMimeType, BitmapFactory.Options options) {
            return new IOException("Exception decoding bitmap, outWidth: " + outWidth + ", outHeight: " + outHeight + ", outMimeType: " + outMimeType + ", inBitmap: " + getInBitmapString(options), e);
        }

        private static void setInBitmap(BitmapFactory.Options options, BitmapPool bitmapPool, int width, int height) {
            Bitmap.Config expectedConfig = null;
            if (Build.VERSION.SDK_INT >= 26) {
                if (options.inPreferredConfig == Bitmap.Config.HARDWARE) {
                    return;
                } else {
                    expectedConfig = options.outConfig;
                }
            }
            if (expectedConfig == null) {
                expectedConfig = options.inPreferredConfig;
            }
            options.inBitmap = bitmapPool.getDirty(width, height, expectedConfig);
        }

        private static synchronized BitmapFactory.Options getDefaultOptions() {
            BitmapFactory.Options decodeBitmapOptions;
            synchronized (OPTIONS_QUEUE) {
                try {
                    decodeBitmapOptions = OPTIONS_QUEUE.poll();
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (decodeBitmapOptions == null) {
                decodeBitmapOptions = new BitmapFactory.Options();
                resetOptions(decodeBitmapOptions);
            }
            return decodeBitmapOptions;
        }

        private static void releaseOptions(BitmapFactory.Options decodeBitmapOptions) {
            resetOptions(decodeBitmapOptions);
            synchronized (OPTIONS_QUEUE) {
                OPTIONS_QUEUE.offer(decodeBitmapOptions);
            }
        }

        private static void resetOptions(BitmapFactory.Options decodeBitmapOptions) {
            decodeBitmapOptions.inTempStorage = null;
            decodeBitmapOptions.inDither = false;
            decodeBitmapOptions.inScaled = false;
            decodeBitmapOptions.inSampleSize = 1;
            decodeBitmapOptions.inPreferredConfig = null;
            decodeBitmapOptions.inJustDecodeBounds = false;
            decodeBitmapOptions.inDensity = 0;
            decodeBitmapOptions.inTargetDensity = 0;
            if (Build.VERSION.SDK_INT >= 26) {
                decodeBitmapOptions.inPreferredColorSpace = null;
                decodeBitmapOptions.outColorSpace = null;
                decodeBitmapOptions.outConfig = null;
            }
            decodeBitmapOptions.outWidth = 0;
            decodeBitmapOptions.outHeight = 0;
            decodeBitmapOptions.outMimeType = null;
            decodeBitmapOptions.inBitmap = null;
            decodeBitmapOptions.inMutable = true;
        }

        private static boolean isRotationRequired(int degreesToRotate) {
            return degreesToRotate == 90 || degreesToRotate == 270;
        }
    }
