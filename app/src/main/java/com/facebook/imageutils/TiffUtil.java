package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.RotationOptions;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TiffUtil.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J \u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0007J \u0010\u0015\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/imageutils/TiffUtil;", "", "()V", "TAG", "Ljava/lang/Class;", "TIFF_BYTE_ORDER_BIG_END", "", "TIFF_BYTE_ORDER_LITTLE_END", "TIFF_TAG_ORIENTATION", "TIFF_TYPE_SHORT", "getAutoRotateAngleFromOrientation", "orientation", "getOrientationFromTiffEntry", "stream", "Ljava/io/InputStream;", "length", "isLittleEndian", "", "moveToTiffEntryWithTag", "tagToFind", "readOrientationFromTIFF", "readTiffHeader", "tiffHeader", "Lcom/facebook/imageutils/TiffUtil$TiffHeader;", "TiffHeader", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TiffUtil {
    public static final TiffUtil INSTANCE = new TiffUtil();
    private static final Class<?> TAG = TiffUtil.class;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    private TiffUtil() {
    }

    @JvmStatic
    public static final int getAutoRotateAngleFromOrientation(int orientation) {
        switch (orientation) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            case 7:
            default:
                return 0;
            case 3:
                return RotationOptions.ROTATE_180;
            case 6:
                return 90;
            case 8:
                return RotationOptions.ROTATE_270;
        }
    }

    @JvmStatic
    public static final int readOrientationFromTIFF(InputStream stream, int length) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        TiffHeader tiffHeader = new TiffHeader();
        int remainingLength = INSTANCE.readTiffHeader(stream, length, tiffHeader);
        int toSkip = tiffHeader.getFirstIfdOffset() - 8;
        if (remainingLength == 0 || toSkip > remainingLength) {
            return 0;
        }
        stream.skip(toSkip);
        return INSTANCE.getOrientationFromTiffEntry(stream, INSTANCE.moveToTiffEntryWithTag(stream, remainingLength - toSkip, tiffHeader.getIsLittleEndian(), TIFF_TAG_ORIENTATION), tiffHeader.getIsLittleEndian());
    }

    private final int readTiffHeader(InputStream stream, int length, TiffHeader tiffHeader) throws IOException {
        if (length <= 8) {
            return 0;
        }
        tiffHeader.setByteOrder(StreamProcessor.readPackedInt(stream, 4, false));
        int remainingLength = length - 4;
        if (tiffHeader.getByteOrder() != 1229531648 && tiffHeader.getByteOrder() != 1296891946) {
            FLog.e(TAG, "Invalid TIFF header");
            return 0;
        }
        tiffHeader.setLittleEndian(tiffHeader.getByteOrder() == 1229531648);
        tiffHeader.setFirstIfdOffset(StreamProcessor.readPackedInt(stream, 4, tiffHeader.getIsLittleEndian()));
        int remainingLength2 = remainingLength - 4;
        if (tiffHeader.getFirstIfdOffset() < 8 || tiffHeader.getFirstIfdOffset() - 8 > remainingLength2) {
            FLog.e(TAG, "Invalid offset");
            return 0;
        }
        return remainingLength2;
    }

    private final int moveToTiffEntryWithTag(InputStream stream, int length, boolean isLittleEndian, int tagToFind) throws IOException {
        if (length < 14) {
            return 0;
        }
        int tag = StreamProcessor.readPackedInt(stream, 2, isLittleEndian);
        int remainingLength = length - 2;
        while (true) {
            int numEntries = tag - 1;
            if (tag <= 0 || remainingLength < 12) {
                break;
            }
            int tag2 = StreamProcessor.readPackedInt(stream, 2, isLittleEndian);
            int remainingLength2 = remainingLength - 2;
            if (tag2 == tagToFind) {
                return remainingLength2;
            }
            stream.skip(10L);
            remainingLength = remainingLength2 - 10;
            tag = numEntries;
        }
        return 0;
    }

    private final int getOrientationFromTiffEntry(InputStream stream, int length, boolean isLittleEndian) throws IOException {
        if (length < 10) {
            return 0;
        }
        int type = StreamProcessor.readPackedInt(stream, 2, isLittleEndian);
        if (type != 3) {
            return 0;
        }
        int count = StreamProcessor.readPackedInt(stream, 4, isLittleEndian);
        if (count != 1) {
            return 0;
        }
        return StreamProcessor.readPackedInt(stream, 2, isLittleEndian);
    }

    /* JADX INFO: compiled from: TiffUtil.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/facebook/imageutils/TiffUtil$TiffHeader;", "", "()V", "byteOrder", "", "getByteOrder", "()I", "setByteOrder", "(I)V", "firstIfdOffset", "getFirstIfdOffset", "setFirstIfdOffset", "isLittleEndian", "", "()Z", "setLittleEndian", "(Z)V", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class TiffHeader {
        private int byteOrder;
        private int firstIfdOffset;
        private boolean isLittleEndian;

        /* JADX INFO: renamed from: isLittleEndian, reason: from getter */
        public final boolean getIsLittleEndian() {
            return this.isLittleEndian;
        }

        public final void setLittleEndian(boolean z) {
            this.isLittleEndian = z;
        }

        public final int getByteOrder() {
            return this.byteOrder;
        }

        public final void setByteOrder(int i) {
            this.byteOrder = i;
        }

        public final int getFirstIfdOffset() {
            return this.firstIfdOffset;
        }

        public final void setFirstIfdOffset(int i) {
            this.firstIfdOffset = i;
        }
    }
}
