package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: loaded from: classes11.dex */
public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = StandardGifDecoder.class.getSimpleName();
    private int[] act;
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(GifDecoder.BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData) {
        this(provider, gifHeader, rawData, 1);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData, int sampleSize) {
        this(provider);
        setData(gifHeader, rawData, sampleSize);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider provider) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = provider;
        this.header = new GifHeader();
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.header.width;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.header.height;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public ByteBuffer getData() {
        return this.rawData;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getDelay(int n) {
        if (n < 0 || n >= this.header.frameCount) {
            return -1;
        }
        int delay = this.header.frames.get(n).delay;
        return delay;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return 0;
        }
        return getDelay(this.framePointer);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.header.frameCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to decode frame, frameCount=" + this.header.frameCount + ", framePointer=" + this.framePointer);
            }
            this.status = 1;
        }
        if (this.status == 1 || this.status == 2) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to decode frame, status=" + this.status);
            }
            return null;
        }
        this.status = 0;
        if (this.block == null) {
            this.block = this.bitmapProvider.obtainByteArray(255);
        }
        GifFrame currentFrame = this.header.frames.get(this.framePointer);
        GifFrame previousFrame = null;
        int previousIndex = this.framePointer - 1;
        if (previousIndex >= 0) {
            previousFrame = this.header.frames.get(previousIndex);
        }
        this.act = currentFrame.lct != null ? currentFrame.lct : this.header.gct;
        if (this.act == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "No valid color table found for frame #" + this.framePointer);
            }
            this.status = 1;
            return null;
        }
        if (currentFrame.transparency) {
            System.arraycopy(this.act, 0, this.pct, 0, this.act.length);
            this.act = this.pct;
            this.act[currentFrame.transIndex] = 0;
            if (currentFrame.dispose == 2 && this.framePointer == 0) {
                this.isFirstFrameTransparent = true;
            }
        }
        return setPixels(currentFrame, previousFrame);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public int read(InputStream is, int contentLength) {
        if (is != null) {
            int capacity = contentLength > 0 ? contentLength + 4096 : 16384;
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(capacity);
                byte[] data = new byte[16384];
                while (true) {
                    int nRead = is.read(data, 0, data.length);
                    if (nRead == -1) {
                        break;
                    }
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                read(buffer.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void clear() {
        this.header = null;
        if (this.mainPixels != null) {
            this.bitmapProvider.release(this.mainPixels);
        }
        if (this.mainScratch != null) {
            this.bitmapProvider.release(this.mainScratch);
        }
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        if (this.block != null) {
            this.bitmapProvider.release(this.block);
        }
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader header, byte[] data) {
        setData(header, ByteBuffer.wrap(data));
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader header, ByteBuffer buffer) {
        setData(header, buffer, 1);
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader header, ByteBuffer buffer, int sampleSize) {
        try {
            if (sampleSize <= 0) {
                throw new IllegalArgumentException("Sample size must be >=0, not: " + sampleSize);
            }
            int sampleSize2 = Integer.highestOneBit(sampleSize);
            this.status = 0;
            this.header = header;
            this.framePointer = -1;
            this.rawData = buffer.asReadOnlyBuffer();
            this.rawData.position(0);
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.savePrevious = false;
            for (GifFrame frame : header.frames) {
                if (frame.dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
            this.sampleSize = sampleSize2;
            this.downsampledWidth = header.width / sampleSize2;
            this.downsampledHeight = header.height / sampleSize2;
            this.mainPixels = this.bitmapProvider.obtainByteArray(header.width * header.height);
            this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
        } catch (Throwable th) {
            throw th;
        }
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public synchronized int read(byte[] data) {
        this.header = getHeaderParser().setData(data).parseHeader();
        if (data != null) {
            setData(this.header, data);
        }
        return this.status;
    }

    @Override // com.bumptech.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
        }
        this.bitmapConfig = config;
    }

    private Bitmap setPixels(GifFrame currentFrame, GifFrame previousFrame) {
        int[] dest = this.mainScratch;
        if (previousFrame == null) {
            if (this.previousImage != null) {
                this.bitmapProvider.release(this.previousImage);
            }
            this.previousImage = null;
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose == 3 && this.previousImage == null) {
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose > 0) {
            if (previousFrame.dispose != 2) {
                if (previousFrame.dispose == 3 && this.previousImage != null) {
                    this.previousImage.getPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
                }
            } else {
                int c = 0;
                if (!currentFrame.transparency) {
                    c = this.header.bgColor;
                    if (currentFrame.lct != null && this.header.bgIndex == currentFrame.transIndex) {
                        c = 0;
                    }
                }
                int downsampledIH = previousFrame.ih / this.sampleSize;
                int downsampledIY = previousFrame.iy / this.sampleSize;
                int downsampledIW = previousFrame.iw / this.sampleSize;
                int downsampledIX = previousFrame.ix / this.sampleSize;
                int topLeft = (this.downsampledWidth * downsampledIY) + downsampledIX;
                int bottomLeft = (this.downsampledWidth * downsampledIH) + topLeft;
                int left = topLeft;
                while (left < bottomLeft) {
                    int right = left + downsampledIW;
                    for (int pointer = left; pointer < right; pointer++) {
                        dest[pointer] = c;
                    }
                    int right2 = this.downsampledWidth;
                    left += right2;
                }
            }
        }
        decodeBitmapData(currentFrame);
        if (currentFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(currentFrame);
        } else {
            copyIntoScratchFast(currentFrame);
        }
        if (this.savePrevious && (currentFrame.dispose == 0 || currentFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
        }
        Bitmap result = getNextBitmap();
        result.setPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
        return result;
    }

    private void copyIntoScratchFast(GifFrame currentFrame) {
        GifFrame gifFrame = currentFrame;
        int[] dest = this.mainScratch;
        int downsampledIH = gifFrame.ih;
        int downsampledIY = gifFrame.iy;
        int downsampledIW = gifFrame.iw;
        int downsampledIX = gifFrame.ix;
        boolean isFirstFrame = this.framePointer == 0;
        int width = this.downsampledWidth;
        byte[] mainPixels = this.mainPixels;
        int[] act = this.act;
        byte transparentColorIndex = -1;
        int i = 0;
        while (i < downsampledIH) {
            int line = i + downsampledIY;
            int k = line * width;
            int dx = k + downsampledIX;
            int dlim = dx + downsampledIW;
            if (k + width < dlim) {
                dlim = k + width;
            }
            int sx = gifFrame.iw * i;
            int dx2 = dx;
            while (dx2 < dlim) {
                int dx3 = dx2;
                byte dx4 = mainPixels[sx];
                int[] dest2 = dest;
                int currentColorIndex = dx4 & 255;
                if (currentColorIndex != transparentColorIndex) {
                    int color = act[currentColorIndex];
                    if (color != 0) {
                        dest2[dx3] = color;
                    } else {
                        transparentColorIndex = dx4;
                    }
                }
                sx++;
                dx2 = dx3 + 1;
                dest = dest2;
            }
            i++;
            gifFrame = currentFrame;
        }
        this.isFirstFrameTransparent = Boolean.valueOf((this.isFirstFrameTransparent != null && this.isFirstFrameTransparent.booleanValue()) || (this.isFirstFrameTransparent == null && isFirstFrame && transparentColorIndex != -1));
    }

    private void copyCopyIntoScratchRobust(GifFrame currentFrame) {
        int[] dest = this.mainScratch;
        int downsampledIH = currentFrame.ih / this.sampleSize;
        int downsampledIY = currentFrame.iy / this.sampleSize;
        int downsampledIW = currentFrame.iw / this.sampleSize;
        int downsampledIX = currentFrame.ix / this.sampleSize;
        int pass = 1;
        int inc = 8;
        int iline = 0;
        boolean isFirstFrame = this.framePointer == 0;
        int sampleSize = this.sampleSize;
        int downsampledWidth = this.downsampledWidth;
        int downsampledHeight = this.downsampledHeight;
        byte[] mainPixels = this.mainPixels;
        int[] act = this.act;
        Boolean isFirstFrameTransparent = this.isFirstFrameTransparent;
        Boolean isFirstFrameTransparent2 = isFirstFrameTransparent;
        int line = 0;
        while (line < downsampledIH) {
            int line2 = line;
            int i = line;
            if (currentFrame.interlace) {
                if (iline >= downsampledIH) {
                    pass++;
                    switch (pass) {
                        case 2:
                            iline = 4;
                            break;
                        case 3:
                            iline = 2;
                            inc = 4;
                            break;
                        case 4:
                            iline = 1;
                            inc = 2;
                            break;
                    }
                }
                line2 = iline;
                iline += inc;
            }
            int line3 = line2 + downsampledIY;
            int line4 = downsampledIH;
            boolean isNotDownsampling = sampleSize == 1;
            if (line3 < downsampledHeight) {
                int k = line3 * downsampledWidth;
                int dx = k + downsampledIX;
                int dlim = dx + downsampledIW;
                boolean isNotDownsampling2 = isNotDownsampling;
                if (k + downsampledWidth < dlim) {
                    dlim = k + downsampledWidth;
                }
                int sx = currentFrame.iw * i * sampleSize;
                if (isNotDownsampling2) {
                    int dx2 = sx;
                    int currentColorIndex = dx;
                    while (currentColorIndex < dlim) {
                        int dx3 = currentColorIndex;
                        int currentColorIndex2 = mainPixels[dx2] & 255;
                        int averageColor = act[currentColorIndex2];
                        if (averageColor != 0) {
                            dest[dx3] = averageColor;
                        } else if (isFirstFrame && isFirstFrameTransparent2 == null) {
                            isFirstFrameTransparent2 = true;
                        }
                        dx2 += sampleSize;
                        currentColorIndex = dx3 + 1;
                    }
                } else {
                    int sx2 = sx + ((dlim - dx) * sampleSize);
                    int downsampledIW2 = dx;
                    int downsampledIY2 = sx;
                    while (downsampledIW2 < dlim) {
                        int dlim2 = dlim;
                        int averageColor2 = averageColorsNear(downsampledIY2, sx2, currentFrame.iw);
                        if (averageColor2 != 0) {
                            dest[downsampledIW2] = averageColor2;
                        } else if (isFirstFrame && isFirstFrameTransparent2 == null) {
                            isFirstFrameTransparent2 = true;
                        }
                        downsampledIY2 += sampleSize;
                        downsampledIW2++;
                        dlim = dlim2;
                    }
                }
            }
            line = i + 1;
            downsampledIH = line4;
            downsampledIY = downsampledIY;
            downsampledIW = downsampledIW;
        }
        if (this.isFirstFrameTransparent == null) {
            this.isFirstFrameTransparent = Boolean.valueOf(isFirstFrameTransparent2 == null ? false : isFirstFrameTransparent2.booleanValue());
        }
    }

    private int averageColorsNear(int positionInMainPixels, int maxPositionInMainPixels, int currentFrameIw) {
        int alphaSum = 0;
        int redSum = 0;
        int greenSum = 0;
        int blueSum = 0;
        int totalAdded = 0;
        for (int i = positionInMainPixels; i < this.sampleSize + positionInMainPixels && i < this.mainPixels.length && i < maxPositionInMainPixels; i++) {
            int currentColorIndex = this.mainPixels[i] & 255;
            int currentColor = this.act[currentColorIndex];
            if (currentColor != 0) {
                alphaSum += (currentColor >> 24) & 255;
                redSum += (currentColor >> 16) & 255;
                greenSum += (currentColor >> 8) & 255;
                blueSum += currentColor & 255;
                totalAdded++;
            }
        }
        for (int i2 = positionInMainPixels + currentFrameIw; i2 < positionInMainPixels + currentFrameIw + this.sampleSize && i2 < this.mainPixels.length && i2 < maxPositionInMainPixels; i2++) {
            int currentColorIndex2 = this.mainPixels[i2] & 255;
            int currentColor2 = this.act[currentColorIndex2];
            if (currentColor2 != 0) {
                alphaSum += (currentColor2 >> 24) & 255;
                redSum += (currentColor2 >> 16) & 255;
                greenSum += (currentColor2 >> 8) & 255;
                blueSum += currentColor2 & 255;
                totalAdded++;
            }
        }
        if (totalAdded == 0) {
            return 0;
        }
        return ((alphaSum / totalAdded) << 24) | ((redSum / totalAdded) << 16) | ((greenSum / totalAdded) << 8) | (blueSum / totalAdded);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 3521. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r29) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private int readBlock() {
        int blockSize = readByte();
        if (blockSize <= 0) {
            return blockSize;
        }
        this.rawData.get(this.block, 0, Math.min(blockSize, this.rawData.remaining()));
        return blockSize;
    }

    private Bitmap getNextBitmap() {
        Bitmap.Config config = (this.isFirstFrameTransparent == null || this.isFirstFrameTransparent.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig;
        Bitmap result = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, config);
        result.setHasAlpha(true);
        return result;
    }
}
