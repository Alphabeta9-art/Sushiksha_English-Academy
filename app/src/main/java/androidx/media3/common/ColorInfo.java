package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import java.util.Arrays;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: loaded from: classes11.dex */
public final class ColorInfo implements Bundleable {
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    private int hashCode;
    public final byte[] hdrStaticInfo;
    public static final ColorInfo SDR_BT709_LIMITED = new ColorInfo(1, 2, 3, null);
    public static final ColorInfo SRGB_BT709_FULL = new Builder().setColorSpace(1).setColorRange(1).setColorTransfer(2).build();
    private static final String FIELD_COLOR_SPACE = Util.intToStringMaxRadix(0);
    private static final String FIELD_COLOR_RANGE = Util.intToStringMaxRadix(1);
    private static final String FIELD_COLOR_TRANSFER = Util.intToStringMaxRadix(2);
    private static final String FIELD_HDR_STATIC_INFO = Util.intToStringMaxRadix(3);
    public static final Bundleable.Creator<ColorInfo> CREATOR = new Bundleable.Creator() { // from class: androidx.media3.common.ColorInfo$$ExternalSyntheticLambda0
        @Override // androidx.media3.common.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            return ColorInfo.lambda$static$0(bundle);
        }
    };

    public static final class Builder {
        private int colorRange;
        private int colorSpace;
        private int colorTransfer;
        private byte[] hdrStaticInfo;

        public Builder() {
            this.colorSpace = -1;
            this.colorRange = -1;
            this.colorTransfer = -1;
        }

        private Builder(ColorInfo colorInfo) {
            this.colorSpace = colorInfo.colorSpace;
            this.colorRange = colorInfo.colorRange;
            this.colorTransfer = colorInfo.colorTransfer;
            this.hdrStaticInfo = colorInfo.hdrStaticInfo;
        }

        public Builder setColorSpace(int colorSpace) {
            this.colorSpace = colorSpace;
            return this;
        }

        public Builder setColorRange(int colorRange) {
            this.colorRange = colorRange;
            return this;
        }

        public Builder setColorTransfer(int colorTransfer) {
            this.colorTransfer = colorTransfer;
            return this;
        }

        public Builder setHdrStaticInfo(byte[] hdrStaticInfo) {
            this.hdrStaticInfo = hdrStaticInfo;
            return this;
        }

        public ColorInfo build() {
            return new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, this.hdrStaticInfo);
        }
    }

    @Pure
    public static int isoColorPrimariesToColorSpace(int isoColorPrimaries) {
        switch (isoColorPrimaries) {
            case 1:
                return 1;
            case 2:
            case 3:
            case 8:
            default:
                return -1;
            case 4:
            case 5:
            case 6:
            case 7:
                return 2;
            case 9:
                return 6;
        }
    }

    @Pure
    public static int isoTransferCharacteristicsToColorTransfer(int isoTransferCharacteristics) {
        switch (isoTransferCharacteristics) {
            case 1:
            case 6:
            case 7:
                return 3;
            case 4:
                return 10;
            case 13:
                return 2;
            case 16:
                return 6;
            case 18:
                return 7;
            default:
                return -1;
        }
    }

    public static boolean isTransferHdr(ColorInfo colorInfo) {
        return colorInfo != null && (colorInfo.colorTransfer == 7 || colorInfo.colorTransfer == 6);
    }

    @Deprecated
    public ColorInfo(int colorSpace, int colorRange, int colorTransfer, byte[] hdrStaticInfo) {
        this.colorSpace = colorSpace;
        this.colorRange = colorRange;
        this.colorTransfer = colorTransfer;
        this.hdrStaticInfo = hdrStaticInfo;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean isValid() {
        return (this.colorSpace == -1 || this.colorRange == -1 || this.colorTransfer == -1) ? false : true;
    }

    public String toLogString() {
        if (!isValid()) {
            return "NA";
        }
        return Util.formatInvariant("%s/%s/%s", colorSpaceToString(this.colorSpace), colorRangeToString(this.colorRange), colorTransferToString(this.colorTransfer));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorInfo other = (ColorInfo) obj;
        if (this.colorSpace == other.colorSpace && this.colorRange == other.colorRange && this.colorTransfer == other.colorTransfer && Arrays.equals(this.hdrStaticInfo, other.hdrStaticInfo)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ColorInfo(" + colorSpaceToString(this.colorSpace) + ", " + colorRangeToString(this.colorRange) + ", " + colorTransferToString(this.colorTransfer) + ", " + (this.hdrStaticInfo != null) + ")";
    }

    private static String colorSpaceToString(int colorSpace) {
        switch (colorSpace) {
            case -1:
                return "Unset color space";
            case 1:
                return "BT709";
            case 2:
                return "BT601";
            case 6:
                return "BT2020";
            default:
                return "Undefined color space";
        }
    }

    private static String colorTransferToString(int colorTransfer) {
        switch (colorTransfer) {
            case -1:
                return "Unset color transfer";
            case 0:
            case 4:
            case 5:
            case 8:
            case 9:
            default:
                return "Undefined color transfer";
            case 1:
                return "Linear";
            case 2:
                return "sRGB";
            case 3:
                return "SDR SMPTE 170M";
            case 6:
                return "ST2084 PQ";
            case 7:
                return "HLG";
            case 10:
                return "Gamma 2.2";
        }
    }

    private static String colorRangeToString(int colorRange) {
        switch (colorRange) {
            case -1:
                return "Unset color range";
            case 0:
            default:
                return "Undefined color range";
            case 1:
                return "Full range";
            case 2:
                return "Limited range";
        }
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int result = (17 * 31) + this.colorSpace;
            this.hashCode = (((((result * 31) + this.colorRange) * 31) + this.colorTransfer) * 31) + Arrays.hashCode(this.hdrStaticInfo);
        }
        int result2 = this.hashCode;
        return result2;
    }

    @Override // androidx.media3.common.Bundleable
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_COLOR_SPACE, this.colorSpace);
        bundle.putInt(FIELD_COLOR_RANGE, this.colorRange);
        bundle.putInt(FIELD_COLOR_TRANSFER, this.colorTransfer);
        bundle.putByteArray(FIELD_HDR_STATIC_INFO, this.hdrStaticInfo);
        return bundle;
    }

    static /* synthetic */ ColorInfo lambda$static$0(Bundle bundle) {
        return new ColorInfo(bundle.getInt(FIELD_COLOR_SPACE, -1), bundle.getInt(FIELD_COLOR_RANGE, -1), bundle.getInt(FIELD_COLOR_TRANSFER, -1), bundle.getByteArray(FIELD_HDR_STATIC_INFO));
    }
}
