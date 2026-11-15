package androidx.media3.container;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes11.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    private static final int H264_NAL_UNIT_TYPE_SEI = 6;
    private static final int H264_NAL_UNIT_TYPE_SPS = 7;
    private static final int H265_NAL_UNIT_TYPE_PREFIX_SEI = 39;
    public static final int NAL_UNIT_TYPE_AUD = 9;
    public static final int NAL_UNIT_TYPE_IDR = 5;
    public static final int NAL_UNIT_TYPE_NON_IDR = 1;
    public static final int NAL_UNIT_TYPE_PARTITION_A = 2;
    public static final int NAL_UNIT_TYPE_PPS = 8;
    public static final int NAL_UNIT_TYPE_SEI = 6;
    public static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    public static final class SpsData {
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int constraintsFlagsAndReservedZero2Bits;
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int levelIdc;
        public final int maxNumRefFrames;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthHeightRatio;
        public final int profileIdc;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int profileIdc, int constraintsFlagsAndReservedZero2Bits, int levelIdc, int seqParameterSetId, int maxNumRefFrames, int width, int height, float pixelWidthHeightRatio, boolean separateColorPlaneFlag, boolean frameMbsOnlyFlag, int frameNumLength, int picOrderCountType, int picOrderCntLsbLength, boolean deltaPicOrderAlwaysZeroFlag, int colorSpace, int colorRange, int colorTransfer) {
            this.profileIdc = profileIdc;
            this.constraintsFlagsAndReservedZero2Bits = constraintsFlagsAndReservedZero2Bits;
            this.levelIdc = levelIdc;
            this.seqParameterSetId = seqParameterSetId;
            this.maxNumRefFrames = maxNumRefFrames;
            this.width = width;
            this.height = height;
            this.pixelWidthHeightRatio = pixelWidthHeightRatio;
            this.separateColorPlaneFlag = separateColorPlaneFlag;
            this.frameMbsOnlyFlag = frameMbsOnlyFlag;
            this.frameNumLength = frameNumLength;
            this.picOrderCountType = picOrderCountType;
            this.picOrderCntLsbLength = picOrderCntLsbLength;
            this.deltaPicOrderAlwaysZeroFlag = deltaPicOrderAlwaysZeroFlag;
            this.colorSpace = colorSpace;
            this.colorRange = colorRange;
            this.colorTransfer = colorTransfer;
        }
    }

    public static final class H265SpsData {
        public final int bitDepthChromaMinus8;
        public final int bitDepthLumaMinus8;
        public final int chromaFormatIdc;
        public final int colorRange;
        public final int colorSpace;
        public final int colorTransfer;
        public final int[] constraintBytes;
        public final int generalLevelIdc;
        public final int generalProfileCompatibilityFlags;
        public final int generalProfileIdc;
        public final int generalProfileSpace;
        public final boolean generalTierFlag;
        public final int height;
        public final float pixelWidthHeightRatio;
        public final int seqParameterSetId;
        public final int width;

        public H265SpsData(int generalProfileSpace, boolean generalTierFlag, int generalProfileIdc, int generalProfileCompatibilityFlags, int chromaFormatIdc, int bitDepthLumaMinus8, int bitDepthChromaMinus8, int[] constraintBytes, int generalLevelIdc, int seqParameterSetId, int width, int height, float pixelWidthHeightRatio, int colorSpace, int colorRange, int colorTransfer) {
            this.generalProfileSpace = generalProfileSpace;
            this.generalTierFlag = generalTierFlag;
            this.generalProfileIdc = generalProfileIdc;
            this.generalProfileCompatibilityFlags = generalProfileCompatibilityFlags;
            this.chromaFormatIdc = chromaFormatIdc;
            this.bitDepthLumaMinus8 = bitDepthLumaMinus8;
            this.bitDepthChromaMinus8 = bitDepthChromaMinus8;
            this.constraintBytes = constraintBytes;
            this.generalLevelIdc = generalLevelIdc;
            this.seqParameterSetId = seqParameterSetId;
            this.width = width;
            this.height = height;
            this.pixelWidthHeightRatio = pixelWidthHeightRatio;
            this.colorSpace = colorSpace;
            this.colorRange = colorRange;
            this.colorTransfer = colorTransfer;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int picParameterSetId, int seqParameterSetId, boolean bottomFieldPicOrderInFramePresentFlag) {
            this.picParameterSetId = picParameterSetId;
            this.seqParameterSetId = seqParameterSetId;
            this.bottomFieldPicOrderInFramePresentFlag = bottomFieldPicOrderInFramePresentFlag;
        }
    }

    public static int unescapeStream(byte[] data, int limit) {
        int unescapedLength;
        synchronized (scratchEscapePositionsLock) {
            int position = 0;
            int scratchEscapeCount = 0;
            while (position < limit) {
                position = findNextUnescapeIndex(data, position, limit);
                if (position < limit) {
                    if (scratchEscapePositions.length <= scratchEscapeCount) {
                        scratchEscapePositions = Arrays.copyOf(scratchEscapePositions, scratchEscapePositions.length * 2);
                    }
                    scratchEscapePositions[scratchEscapeCount] = position;
                    position += 3;
                    scratchEscapeCount++;
                }
            }
            unescapedLength = limit - scratchEscapeCount;
            int escapedPosition = 0;
            int unescapedPosition = 0;
            for (int i = 0; i < scratchEscapeCount; i++) {
                int nextEscapePosition = scratchEscapePositions[i];
                int copyLength = nextEscapePosition - escapedPosition;
                System.arraycopy(data, escapedPosition, data, unescapedPosition, copyLength);
                int unescapedPosition2 = unescapedPosition + copyLength;
                int unescapedPosition3 = unescapedPosition2 + 1;
                data[unescapedPosition2] = 0;
                unescapedPosition = unescapedPosition3 + 1;
                data[unescapedPosition3] = 0;
                escapedPosition += copyLength + 3;
            }
            int i2 = unescapedLength - unescapedPosition;
            System.arraycopy(data, escapedPosition, data, unescapedPosition, i2);
        }
        return unescapedLength;
    }

    public static void discardToSps(ByteBuffer data) {
        int length = data.position();
        int consecutiveZeros = 0;
        for (int offset = 0; offset + 1 < length; offset++) {
            int value = data.get(offset) & 255;
            if (consecutiveZeros == 3) {
                if (value == 1 && (data.get(offset + 1) & Ascii.US) == 7) {
                    ByteBuffer offsetData = data.duplicate();
                    offsetData.position(offset - 3);
                    offsetData.limit(length);
                    data.position(0);
                    data.put(offsetData);
                    return;
                }
            } else if (value == 0) {
                consecutiveZeros++;
            }
            if (value != 0) {
                consecutiveZeros = 0;
            }
        }
        data.clear();
    }

    public static boolean isNalUnitSei(String mimeType, byte nalUnitHeaderFirstByte) {
        if ("video/avc".equals(mimeType) && (nalUnitHeaderFirstByte & Ascii.US) == 6) {
            return true;
        }
        return "video/hevc".equals(mimeType) && ((nalUnitHeaderFirstByte & 126) >> 1) == 39;
    }

    public static int getNalUnitType(byte[] data, int offset) {
        return data[offset + 3] & Ascii.US;
    }

    public static int getH265NalUnitType(byte[] data, int offset) {
        return (data[offset + 3] & 126) >> 1;
    }

    public static SpsData parseSpsNalUnit(byte[] nalData, int nalOffset, int nalLimit) {
        return parseSpsNalUnitPayload(nalData, nalOffset + 1, nalLimit);
    }

    public static SpsData parseSpsNalUnitPayload(byte[] bArr, int i, int i2) {
        boolean z;
        int iIsoTransferCharacteristicsToColorTransfer;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, i, i2);
        int bits = parsableNalUnitBitArray.readBits(8);
        int bits2 = parsableNalUnitBitArray.readBits(8);
        int bits3 = parsableNalUnitBitArray.readBits(8);
        int unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = 1;
        boolean bit = false;
        if (bits != 100 && bits != 110 && bits != 122 && bits != 244 && bits != 44 && bits != 83 && bits != 86 && bits != 118 && bits != 128 && bits != 138) {
            z = false;
        } else {
            unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt2 == 3) {
                bit = parsableNalUnitBitArray.readBit();
            }
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            parsableNalUnitBitArray.skipBit();
            if (parsableNalUnitBitArray.readBit()) {
                int i8 = unsignedExpGolombCodedInt2 != 3 ? 8 : 12;
                int i9 = 0;
                while (i9 < i8) {
                    if (parsableNalUnitBitArray.readBit()) {
                        skipScalingList(parsableNalUnitBitArray, i9 < 6 ? 16 : 64);
                    }
                    i9++;
                }
            }
            z = bit;
        }
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        int unsignedExpGolombCodedInt4 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt5 = 0;
        boolean z2 = false;
        if (unsignedExpGolombCodedInt4 == 0) {
            unsignedExpGolombCodedInt5 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 4;
        } else if (unsignedExpGolombCodedInt4 == 1) {
            boolean bit2 = parsableNalUnitBitArray.readBit();
            parsableNalUnitBitArray.readSignedExpGolombCodedInt();
            parsableNalUnitBitArray.readSignedExpGolombCodedInt();
            long unsignedExpGolombCodedInt6 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            for (int i10 = 0; i10 < unsignedExpGolombCodedInt6; i10++) {
                parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            }
            z2 = bit2;
        }
        int unsignedExpGolombCodedInt7 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        parsableNalUnitBitArray.skipBit();
        int unsignedExpGolombCodedInt8 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        int unsignedExpGolombCodedInt9 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
        boolean bit3 = parsableNalUnitBitArray.readBit();
        int i11 = (2 - (bit3 ? 1 : 0)) * unsignedExpGolombCodedInt9;
        if (!bit3) {
            parsableNalUnitBitArray.skipBit();
        }
        parsableNalUnitBitArray.skipBit();
        int i12 = unsignedExpGolombCodedInt8 * 16;
        int i13 = i11 * 16;
        if (parsableNalUnitBitArray.readBit()) {
            int unsignedExpGolombCodedInt10 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt11 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt12 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt13 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt2 == 0) {
                i7 = 2 - (bit3 ? 1 : 0);
                i6 = 1;
            } else {
                if (unsignedExpGolombCodedInt2 == 3) {
                    i4 = 1;
                } else {
                    i4 = 2;
                }
                if (unsignedExpGolombCodedInt2 != 1) {
                    i5 = 1;
                } else {
                    i5 = 2;
                }
                i6 = i4;
                i7 = (2 - (bit3 ? 1 : 0)) * i5;
            }
            i12 -= (unsignedExpGolombCodedInt10 + unsignedExpGolombCodedInt11) * i6;
            i13 -= (unsignedExpGolombCodedInt12 + unsignedExpGolombCodedInt13) * i7;
        }
        int iIsoColorPrimariesToColorSpace = -1;
        int i14 = -1;
        float f = 1.0f;
        if (!parsableNalUnitBitArray.readBit()) {
            iIsoTransferCharacteristicsToColorTransfer = -1;
        } else {
            if (!parsableNalUnitBitArray.readBit()) {
                i3 = -1;
            } else {
                i3 = -1;
                int bits4 = parsableNalUnitBitArray.readBits(8);
                if (bits4 == 255) {
                    int bits5 = parsableNalUnitBitArray.readBits(16);
                    int bits6 = parsableNalUnitBitArray.readBits(16);
                    if (bits5 != 0 && bits6 != 0) {
                        f = bits5 / bits6;
                    }
                } else if (bits4 < ASPECT_RATIO_IDC_VALUES.length) {
                    f = ASPECT_RATIO_IDC_VALUES[bits4];
                } else {
                    Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits4);
                }
            }
            if (parsableNalUnitBitArray.readBit()) {
                parsableNalUnitBitArray.skipBit();
            }
            if (!parsableNalUnitBitArray.readBit()) {
                iIsoTransferCharacteristicsToColorTransfer = -1;
                iIsoColorPrimariesToColorSpace = i3;
            } else {
                parsableNalUnitBitArray.skipBits(3);
                i14 = parsableNalUnitBitArray.readBit() ? 1 : 2;
                if (!parsableNalUnitBitArray.readBit()) {
                    iIsoTransferCharacteristicsToColorTransfer = -1;
                    iIsoColorPrimariesToColorSpace = i3;
                } else {
                    int bits7 = parsableNalUnitBitArray.readBits(8);
                    int bits8 = parsableNalUnitBitArray.readBits(8);
                    parsableNalUnitBitArray.skipBits(8);
                    iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(bits7);
                    iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(bits8);
                }
            }
        }
        return new SpsData(bits, bits2, bits3, unsignedExpGolombCodedInt, unsignedExpGolombCodedInt7, i12, i13, f, z, bit3, unsignedExpGolombCodedInt3, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, z2, iIsoColorPrimariesToColorSpace, i14, iIsoTransferCharacteristicsToColorTransfer);
    }

    public static H265SpsData parseH265SpsNalUnit(byte[] nalData, int nalOffset, int nalLimit) {
        return parseH265SpsNalUnitPayload(nalData, nalOffset + 2, nalLimit);
    }

    public static H265SpsData parseH265SpsNalUnitPayload(byte[] nalData, int nalOffset, int nalLimit) {
        int colorRange;
        int colorTransfer;
        float pixelWidthHeightRatio;
        ParsableNalUnitBitArray data = new ParsableNalUnitBitArray(nalData, nalOffset, nalLimit);
        data.skipBits(4);
        int maxSubLayersMinus1 = data.readBits(3);
        data.skipBit();
        int generalProfileSpace = data.readBits(2);
        boolean generalTierFlag = data.readBit();
        int generalProfileIdc = data.readBits(5);
        int generalProfileCompatibilityFlags = 0;
        for (int generalProfileCompatibilityFlags2 = 0; generalProfileCompatibilityFlags2 < 32; generalProfileCompatibilityFlags2++) {
            if (data.readBit()) {
                generalProfileCompatibilityFlags |= 1 << generalProfileCompatibilityFlags2;
            }
        }
        int[] constraintBytes = new int[6];
        for (int i = 0; i < constraintBytes.length; i++) {
            constraintBytes[i] = data.readBits(8);
        }
        int generalLevelIdc = data.readBits(8);
        int toSkip = 0;
        for (int i2 = 0; i2 < maxSubLayersMinus1; i2++) {
            if (data.readBit()) {
                toSkip += 89;
            }
            if (data.readBit()) {
                toSkip += 8;
            }
        }
        data.skipBits(toSkip);
        if (maxSubLayersMinus1 > 0) {
            data.skipBits((8 - maxSubLayersMinus1) * 2);
        }
        int seqParameterSetId = data.readUnsignedExpGolombCodedInt();
        int chromaFormatIdc = data.readUnsignedExpGolombCodedInt();
        if (chromaFormatIdc == 3) {
            data.skipBit();
        }
        int frameWidth = data.readUnsignedExpGolombCodedInt();
        int frameHeight = data.readUnsignedExpGolombCodedInt();
        if (data.readBit()) {
            int confWinLeftOffset = data.readUnsignedExpGolombCodedInt();
            int confWinRightOffset = data.readUnsignedExpGolombCodedInt();
            int confWinTopOffset = data.readUnsignedExpGolombCodedInt();
            int confWinBottomOffset = data.readUnsignedExpGolombCodedInt();
            int subWidthC = (chromaFormatIdc == 1 || chromaFormatIdc == 2) ? 2 : 1;
            int subHeightC = chromaFormatIdc == 1 ? 2 : 1;
            frameWidth -= (confWinLeftOffset + confWinRightOffset) * subWidthC;
            frameHeight -= (confWinTopOffset + confWinBottomOffset) * subHeightC;
        }
        int bitDepthLumaMinus8 = data.readUnsignedExpGolombCodedInt();
        int bitDepthChromaMinus8 = data.readUnsignedExpGolombCodedInt();
        int log2MaxPicOrderCntLsbMinus4 = data.readUnsignedExpGolombCodedInt();
        for (int i3 = data.readBit() ? 0 : maxSubLayersMinus1; i3 <= maxSubLayersMinus1; i3++) {
            data.readUnsignedExpGolombCodedInt();
            data.readUnsignedExpGolombCodedInt();
            data.readUnsignedExpGolombCodedInt();
        }
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        data.readUnsignedExpGolombCodedInt();
        boolean scalingListEnabled = data.readBit();
        if (scalingListEnabled && data.readBit()) {
            skipH265ScalingList(data);
        }
        data.skipBits(2);
        if (data.readBit()) {
            data.skipBits(8);
            data.readUnsignedExpGolombCodedInt();
            data.readUnsignedExpGolombCodedInt();
            data.skipBit();
        }
        skipShortTermReferencePictureSets(data);
        if (data.readBit()) {
            int numLongTermRefPicsSps = data.readUnsignedExpGolombCodedInt();
            for (int i4 = 0; i4 < numLongTermRefPicsSps; i4++) {
                int ltRefPicPocLsbSpsLength = log2MaxPicOrderCntLsbMinus4 + 4;
                data.skipBits(ltRefPicPocLsbSpsLength + 1);
            }
        }
        data.skipBits(2);
        int colorSpace = -1;
        int colorRange2 = -1;
        int colorTransfer2 = -1;
        float pixelWidthHeightRatio2 = 1.0f;
        if (!data.readBit()) {
            colorRange = -1;
            colorTransfer = -1;
            pixelWidthHeightRatio = 1.0f;
        } else {
            if (data.readBit()) {
                int aspectRatioIdc = data.readBits(8);
                if (aspectRatioIdc == 255) {
                    int sarWidth = data.readBits(16);
                    int sarHeight = data.readBits(16);
                    if (sarWidth != 0 && sarHeight != 0) {
                        pixelWidthHeightRatio2 = sarWidth / sarHeight;
                    }
                } else if (aspectRatioIdc < ASPECT_RATIO_IDC_VALUES.length) {
                    pixelWidthHeightRatio2 = ASPECT_RATIO_IDC_VALUES[aspectRatioIdc];
                } else {
                    Log.w(TAG, "Unexpected aspect_ratio_idc value: " + aspectRatioIdc);
                }
            }
            if (data.readBit()) {
                data.skipBit();
            }
            if (data.readBit()) {
                data.skipBits(3);
                int colorRange3 = data.readBit() ? 1 : 2;
                if (!data.readBit()) {
                    colorRange2 = colorRange3;
                } else {
                    int colorPrimaries = data.readBits(8);
                    int transferCharacteristics = data.readBits(8);
                    data.skipBits(8);
                    colorSpace = ColorInfo.isoColorPrimariesToColorSpace(colorPrimaries);
                    colorTransfer2 = ColorInfo.isoTransferCharacteristicsToColorTransfer(transferCharacteristics);
                    colorRange2 = colorRange3;
                }
            }
            if (data.readBit()) {
                data.readUnsignedExpGolombCodedInt();
                data.readUnsignedExpGolombCodedInt();
            }
            data.skipBit();
            if (!data.readBit()) {
                colorRange = colorRange2;
                colorTransfer = colorTransfer2;
                pixelWidthHeightRatio = pixelWidthHeightRatio2;
            } else {
                frameHeight *= 2;
                colorRange = colorRange2;
                colorTransfer = colorTransfer2;
                pixelWidthHeightRatio = pixelWidthHeightRatio2;
            }
        }
        int bitDepthChromaMinus9 = colorSpace;
        return new H265SpsData(generalProfileSpace, generalTierFlag, generalProfileIdc, generalProfileCompatibilityFlags, chromaFormatIdc, bitDepthLumaMinus8, bitDepthChromaMinus8, constraintBytes, generalLevelIdc, seqParameterSetId, frameWidth, frameHeight, pixelWidthHeightRatio, bitDepthChromaMinus9, colorRange, colorTransfer);
    }

    public static PpsData parsePpsNalUnit(byte[] nalData, int nalOffset, int nalLimit) {
        return parsePpsNalUnitPayload(nalData, nalOffset + 1, nalLimit);
    }

    public static PpsData parsePpsNalUnitPayload(byte[] nalData, int nalOffset, int nalLimit) {
        ParsableNalUnitBitArray data = new ParsableNalUnitBitArray(nalData, nalOffset, nalLimit);
        int picParameterSetId = data.readUnsignedExpGolombCodedInt();
        int seqParameterSetId = data.readUnsignedExpGolombCodedInt();
        data.skipBit();
        boolean bottomFieldPicOrderInFramePresentFlag = data.readBit();
        return new PpsData(picParameterSetId, seqParameterSetId, bottomFieldPicOrderInFramePresentFlag);
    }

    public static int findNalUnit(byte[] data, int startOffset, int endOffset, boolean[] prefixFlags) {
        boolean z;
        boolean z2;
        int length = endOffset - startOffset;
        Assertions.checkState(length >= 0);
        if (length == 0) {
            return endOffset;
        }
        if (prefixFlags[0]) {
            clearPrefixFlags(prefixFlags);
            return startOffset - 3;
        }
        if (length > 1 && prefixFlags[1] && data[startOffset] == 1) {
            clearPrefixFlags(prefixFlags);
            return startOffset - 2;
        }
        if (length > 2 && prefixFlags[2] && data[startOffset] == 0 && data[startOffset + 1] == 1) {
            clearPrefixFlags(prefixFlags);
            return startOffset - 1;
        }
        int limit = endOffset - 1;
        int i = startOffset + 2;
        while (i < limit) {
            if ((data[i] & 254) == 0) {
                if (data[i - 2] == 0 && data[i - 1] == 0 && data[i] == 1) {
                    clearPrefixFlags(prefixFlags);
                    return i - 2;
                }
                i -= 2;
            }
            i += 3;
        }
        if (length > 2) {
            z = data[endOffset + (-3)] == 0 && data[endOffset + (-2)] == 0 && data[endOffset + (-1)] == 1;
        } else if (length == 2) {
            z = prefixFlags[2] && data[endOffset + (-2)] == 0 && data[endOffset + (-1)] == 1;
        } else {
            z = prefixFlags[1] && data[endOffset + (-1)] == 1;
        }
        prefixFlags[0] = z;
        if (length > 1) {
            z2 = data[endOffset + (-2)] == 0 && data[endOffset + (-1)] == 0;
        } else {
            z2 = prefixFlags[2] && data[endOffset + (-1)] == 0;
        }
        prefixFlags[1] = z2;
        prefixFlags[2] = data[endOffset + (-1)] == 0;
        return endOffset;
    }

    public static void clearPrefixFlags(boolean[] prefixFlags) {
        prefixFlags[0] = false;
        prefixFlags[1] = false;
        prefixFlags[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bytes, int offset, int limit) {
        for (int i = offset; i < limit - 2; i++) {
            if (bytes[i] == 0 && bytes[i + 1] == 0 && bytes[i + 2] == 3) {
                return i;
            }
        }
        return limit;
    }

    private static void skipScalingList(ParsableNalUnitBitArray bitArray, int size) {
        int lastScale = 8;
        int nextScale = 8;
        for (int i = 0; i < size; i++) {
            if (nextScale != 0) {
                int deltaScale = bitArray.readSignedExpGolombCodedInt();
                nextScale = ((lastScale + deltaScale) + 256) % 256;
            }
            lastScale = nextScale == 0 ? lastScale : nextScale;
        }
    }

    private static void skipH265ScalingList(ParsableNalUnitBitArray bitArray) {
        for (int sizeId = 0; sizeId < 4; sizeId++) {
            int matrixId = 0;
            while (matrixId < 6) {
                int i = 1;
                if (!bitArray.readBit()) {
                    bitArray.readUnsignedExpGolombCodedInt();
                } else {
                    int coefNum = Math.min(64, 1 << ((sizeId << 1) + 4));
                    if (sizeId > 1) {
                        bitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i2 = 0; i2 < coefNum; i2++) {
                        bitArray.readSignedExpGolombCodedInt();
                    }
                }
                if (sizeId == 3) {
                    i = 3;
                }
                matrixId += i;
            }
        }
    }

    private static void skipShortTermReferencePictureSets(ParsableNalUnitBitArray parsableNalUnitBitArray) {
        int unsignedExpGolombCodedInt;
        int unsignedExpGolombCodedInt2;
        int[] iArrCopyOf;
        int[] iArrCopyOf2;
        int unsignedExpGolombCodedInt3 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
        int i = -1;
        int i2 = -1;
        boolean z = false;
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        int i3 = 0;
        while (i3 < unsignedExpGolombCodedInt3) {
            if ((i3 == 0 || !parsableNalUnitBitArray.readBit()) ? z : true) {
                int i4 = i + i2;
                int unsignedExpGolombCodedInt4 = (1 - ((parsableNalUnitBitArray.readBit() ? 1 : 0) * 2)) * (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                boolean[] zArr = new boolean[i4 + 1];
                for (int i5 = 0; i5 <= i4; i5++) {
                    if (!parsableNalUnitBitArray.readBit()) {
                        zArr[i5] = parsableNalUnitBitArray.readBit();
                    } else {
                        zArr[i5] = true;
                    }
                }
                int i6 = 0;
                int[] iArr3 = new int[i4 + 1];
                int[] iArr4 = new int[i4 + 1];
                for (int i7 = i2 - 1; i7 >= 0; i7--) {
                    int i8 = iArr2[i7] + unsignedExpGolombCodedInt4;
                    if (i8 < 0 && zArr[i + i7]) {
                        iArr3[i6] = i8;
                        i6++;
                    }
                }
                if (unsignedExpGolombCodedInt4 < 0 && zArr[i4]) {
                    iArr3[i6] = unsignedExpGolombCodedInt4;
                    i6++;
                }
                for (int i9 = 0; i9 < i; i9++) {
                    int i10 = iArr[i9] + unsignedExpGolombCodedInt4;
                    if (i10 < 0 && zArr[i9]) {
                        iArr3[i6] = i10;
                        i6++;
                    }
                }
                unsignedExpGolombCodedInt = i6;
                iArrCopyOf = Arrays.copyOf(iArr3, unsignedExpGolombCodedInt);
                int i11 = 0;
                for (int i12 = i - 1; i12 >= 0; i12--) {
                    int i13 = iArr[i12] + unsignedExpGolombCodedInt4;
                    if (i13 > 0 && zArr[i12]) {
                        iArr4[i11] = i13;
                        i11++;
                    }
                }
                if (unsignedExpGolombCodedInt4 > 0 && zArr[i4]) {
                    iArr4[i11] = unsignedExpGolombCodedInt4;
                    i11++;
                }
                for (int i14 = 0; i14 < i2; i14++) {
                    int i15 = iArr2[i14] + unsignedExpGolombCodedInt4;
                    if (i15 > 0 && zArr[i + i14]) {
                        iArr4[i11] = i15;
                        i11++;
                    }
                }
                unsignedExpGolombCodedInt2 = i11;
                iArrCopyOf2 = Arrays.copyOf(iArr4, unsignedExpGolombCodedInt2);
            } else {
                unsignedExpGolombCodedInt = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                unsignedExpGolombCodedInt2 = parsableNalUnitBitArray.readUnsignedExpGolombCodedInt();
                iArrCopyOf = new int[unsignedExpGolombCodedInt];
                int i16 = 0;
                while (i16 < unsignedExpGolombCodedInt) {
                    iArrCopyOf[i16] = (i16 > 0 ? iArrCopyOf[i16 - 1] : 0) - (parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1);
                    parsableNalUnitBitArray.skipBit();
                    i16++;
                }
                int[] iArr5 = new int[unsignedExpGolombCodedInt2];
                int i17 = 0;
                while (i17 < unsignedExpGolombCodedInt2) {
                    iArr5[i17] = (i17 > 0 ? iArr5[i17 - 1] : 0) + parsableNalUnitBitArray.readUnsignedExpGolombCodedInt() + 1;
                    parsableNalUnitBitArray.skipBit();
                    i17++;
                }
                iArrCopyOf2 = iArr5;
            }
            i = unsignedExpGolombCodedInt;
            i2 = unsignedExpGolombCodedInt2;
            iArr = iArrCopyOf;
            iArr2 = iArrCopyOf2;
            i3++;
            unsignedExpGolombCodedInt3 = unsignedExpGolombCodedInt3;
            z = false;
        }
    }

    private NalUnitUtil() {
    }
}
