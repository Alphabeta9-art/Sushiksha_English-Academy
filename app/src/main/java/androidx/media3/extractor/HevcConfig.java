package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class HevcConfig {
    private static final int SPS_NAL_UNIT_TYPE = 33;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    public static HevcConfig parse(ParsableByteArray data) throws ParserException {
        try {
            data.skipBytes(21);
            int lengthSizeMinusOne = data.readUnsignedByte() & 3;
            int numberOfArrays = data.readUnsignedByte();
            int csdLength = 0;
            int csdStartPosition = data.getPosition();
            for (int i = 0; i < numberOfArrays; i++) {
                data.skipBytes(1);
                int numberOfNalUnits = data.readUnsignedShort();
                for (int j = 0; j < numberOfNalUnits; j++) {
                    int nalUnitLength = data.readUnsignedShort();
                    csdLength += nalUnitLength + 4;
                    data.skipBytes(nalUnitLength);
                }
            }
            data.setPosition(csdStartPosition);
            byte[] buffer = new byte[csdLength];
            int bufferPosition = 0;
            float pixelWidthHeightRatio = 1.0f;
            String codecs = null;
            int colorSpace = -1;
            int colorRange = -1;
            int width = -1;
            int width2 = 0;
            int colorTransfer = -1;
            int height = -1;
            while (width2 < numberOfArrays) {
                int nalUnitType = data.readUnsignedByte() & 63;
                int numberOfNalUnits2 = data.readUnsignedShort();
                int lengthSizeMinusOne2 = lengthSizeMinusOne;
                int lengthSizeMinusOne3 = 0;
                while (lengthSizeMinusOne3 < numberOfNalUnits2) {
                    int nalUnitLength2 = data.readUnsignedShort();
                    int j2 = lengthSizeMinusOne3;
                    int numberOfArrays2 = numberOfArrays;
                    int csdLength2 = csdLength;
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, buffer, bufferPosition, NalUnitUtil.NAL_START_CODE.length);
                    int bufferPosition2 = bufferPosition + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(data.getData(), data.getPosition(), buffer, bufferPosition2, nalUnitLength2);
                    if (nalUnitType == 33 && j2 == 0) {
                        NalUnitUtil.H265SpsData spsData = NalUnitUtil.parseH265SpsNalUnit(buffer, bufferPosition2, bufferPosition2 + nalUnitLength2);
                        width = spsData.width;
                        height = spsData.height;
                        colorSpace = spsData.colorSpace;
                        colorRange = spsData.colorRange;
                        colorTransfer = spsData.colorTransfer;
                        pixelWidthHeightRatio = spsData.pixelWidthHeightRatio;
                        codecs = CodecSpecificDataUtil.buildHevcCodecString(spsData.generalProfileSpace, spsData.generalTierFlag, spsData.generalProfileIdc, spsData.generalProfileCompatibilityFlags, spsData.constraintBytes, spsData.generalLevelIdc);
                    }
                    bufferPosition = bufferPosition2 + nalUnitLength2;
                    data.skipBytes(nalUnitLength2);
                    lengthSizeMinusOne3 = j2 + 1;
                    numberOfArrays = numberOfArrays2;
                    csdLength = csdLength2;
                }
                width2++;
                lengthSizeMinusOne = lengthSizeMinusOne2;
            }
            int lengthSizeMinusOne4 = lengthSizeMinusOne;
            List<byte[]> initializationData = csdLength == 0 ? Collections.emptyList() : Collections.singletonList(buffer);
            return new HevcConfig(initializationData, lengthSizeMinusOne4 + 1, width, height, colorSpace, colorRange, colorTransfer, pixelWidthHeightRatio, codecs);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing HEVC config", e);
        }
    }

    private HevcConfig(List<byte[]> initializationData, int nalUnitLengthFieldLength, int width, int height, int colorSpace, int colorRange, int colorTransfer, float pixelWidthHeightRatio, String codecs) {
        this.initializationData = initializationData;
        this.nalUnitLengthFieldLength = nalUnitLengthFieldLength;
        this.width = width;
        this.height = height;
        this.colorSpace = colorSpace;
        this.colorRange = colorRange;
        this.colorTransfer = colorTransfer;
        this.pixelWidthHeightRatio = pixelWidthHeightRatio;
        this.codecs = codecs;
    }
}
