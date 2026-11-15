package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public final class AvcConfig {
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int width;

    public static AvcConfig parse(ParsableByteArray data) throws ParserException {
        String codecs;
        float pixelWidthHeightRatio;
        int colorTransfer;
        int colorTransfer2;
        int colorRange;
        int colorSpace;
        int height;
        try {
            data.skipBytes(4);
            int nalUnitLengthFieldLength = (data.readUnsignedByte() & 3) + 1;
            if (nalUnitLengthFieldLength == 3) {
                throw new IllegalStateException();
            }
            List<byte[]> initializationData = new ArrayList<>();
            int numSequenceParameterSets = data.readUnsignedByte() & 31;
            for (int j = 0; j < numSequenceParameterSets; j++) {
                initializationData.add(buildNalUnitForChild(data));
            }
            int numPictureParameterSets = data.readUnsignedByte();
            for (int j2 = 0; j2 < numPictureParameterSets; j2++) {
                initializationData.add(buildNalUnitForChild(data));
            }
            if (numSequenceParameterSets <= 0) {
                codecs = null;
                pixelWidthHeightRatio = 1.0f;
                colorTransfer = -1;
                colorTransfer2 = -1;
                colorRange = -1;
                colorSpace = -1;
                height = -1;
            } else {
                byte[] sps = initializationData.get(0);
                NalUnitUtil.SpsData spsData = NalUnitUtil.parseSpsNalUnit(initializationData.get(0), nalUnitLengthFieldLength, sps.length);
                int width = spsData.width;
                int height2 = spsData.height;
                int colorSpace2 = spsData.colorSpace;
                int colorRange2 = spsData.colorRange;
                int colorTransfer3 = spsData.colorTransfer;
                float pixelWidthHeightRatio2 = spsData.pixelWidthHeightRatio;
                String codecs2 = CodecSpecificDataUtil.buildAvcCodecString(spsData.profileIdc, spsData.constraintsFlagsAndReservedZero2Bits, spsData.levelIdc);
                codecs = codecs2;
                pixelWidthHeightRatio = pixelWidthHeightRatio2;
                colorTransfer = colorTransfer3;
                colorTransfer2 = colorRange2;
                colorRange = colorSpace2;
                colorSpace = height2;
                height = width;
            }
            return new AvcConfig(initializationData, nalUnitLengthFieldLength, height, colorSpace, colorRange, colorTransfer2, colorTransfer, pixelWidthHeightRatio, codecs);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing AVC config", e);
        }
    }

    private AvcConfig(List<byte[]> initializationData, int nalUnitLengthFieldLength, int width, int height, int colorSpace, int colorRange, int colorTransfer, float pixelWidthHeightRatio, String codecs) {
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

    private static byte[] buildNalUnitForChild(ParsableByteArray data) {
        int length = data.readUnsignedShort();
        int offset = data.getPosition();
        data.skipBytes(length);
        return CodecSpecificDataUtil.buildNalUnit(data.getData(), offset, length);
    }
}
