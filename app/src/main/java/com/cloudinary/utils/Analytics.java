package com.cloudinary.utils;

import androidx.exifinterface.media.ExifInterface;
import com.cloudinary.Cloudinary;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes11.dex */
public class Analytics {
    public String SDKCode;
    public String SDKSemver;
    public String algoVersion;
    public String featureFlag;
    public String osType;
    public String osVersion;
    public String prodcut;
    private String sdkQueryDelimiter;
    private String sdkTokenQueryKey;
    public String techVersion;

    public Analytics() {
        this("G", Cloudinary.VERSION, System.getProperty("java.version"), "Z", "0.0", "0");
    }

    public Analytics(String sdkCode, String sdkVersion, String techVersion, String osType, String osVersion, String featureFlag) {
        this.sdkTokenQueryKey = "_a";
        this.sdkQueryDelimiter = "=";
        this.algoVersion = "D";
        this.prodcut = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
        this.SDKCode = "";
        this.SDKSemver = "";
        this.techVersion = "";
        this.featureFlag = "0";
        this.SDKCode = sdkCode;
        this.SDKSemver = sdkVersion;
        this.techVersion = techVersion;
        this.osType = osType;
        this.osVersion = osVersion;
        this.featureFlag = featureFlag;
    }

    public Analytics setSDKCode(String SDKCode) {
        this.SDKCode = SDKCode;
        return this;
    }

    public Analytics setSDKSemver(String SDKSemver) {
        this.SDKSemver = SDKSemver;
        return this;
    }

    public Analytics setTechVersion(String techVersion) {
        this.techVersion = techVersion;
        return this;
    }

    public Analytics setFeatureFlag(String flag) {
        this.featureFlag = flag;
        return this;
    }

    public String toQueryParam() {
        try {
            return this.sdkTokenQueryKey + this.sdkQueryDelimiter + getAlgorithmVersion() + this.prodcut + getSDKType() + getSDKVersion() + getTechVersion() + getOsType() + getOsVersion() + getSDKFeatureFlag();
        } catch (Exception e) {
            return this.sdkTokenQueryKey + this.sdkQueryDelimiter + ExifInterface.LONGITUDE_EAST;
        }
    }

    private String getTechVersion() throws Exception {
        String[] techVersionString = this.techVersion.split("_");
        String[] versions = techVersionString[0].split("\\.");
        return versionArrayToString(versions);
    }

    private String versionArrayToString(String[] versions) throws Exception {
        if (versions.length > 2) {
            versions = (String[]) Arrays.copyOf(versions, versions.length - 1);
        }
        return getPaddedString(StringUtils.join(versions, "."));
    }

    private String versionArrayToOsString(String[] versions) throws Exception {
        if (versions.length > 2) {
            versions = (String[]) Arrays.copyOf(versions, versions.length - 1);
        }
        return getOsVersionString(StringUtils.join(versions, "."));
    }

    private String getOsType() {
        return this.osType != null ? this.osType : "Z";
    }

    private String getOsVersion() throws Exception {
        return this.osVersion != null ? versionArrayToOsString(this.osVersion.split("\\.")) : versionArrayToString(System.getProperty("os.version").split("\\."));
    }

    private String getSDKType() {
        return this.SDKCode;
    }

    private String getAlgorithmVersion() {
        return this.algoVersion;
    }

    private String getSDKFeatureFlag() {
        return this.featureFlag;
    }

    private String getSDKVersion() throws Exception {
        return getPaddedString(this.SDKSemver);
    }

    private String getOsVersionString(String string) throws Exception {
        String[] parts = string.split("\\.");
        String result = "";
        for (String str : parts) {
            int num = Integer.parseInt(str);
            String binaryString = Integer.toBinaryString(num);
            result = result + Base64Map.values.get(StringUtils.padStart(binaryString, 6, '0'));
        }
        return result;
    }

    private String getPaddedString(String string) throws Exception {
        int parts = string.split("\\.").length;
        int paddedStringLength = parts * 6;
        try {
            String paddedReversedSemver = reverseVersion(string);
            int num = Integer.parseInt(StringUtils.join(paddedReversedSemver.split("\\."), ""));
            String paddedBinary = StringUtils.padStart(Integer.toBinaryString(num), paddedStringLength, '0');
            if (paddedBinary.length() % 6 != 0) {
                throw new Exception("Error");
            }
            String result = "";
            List<String> resultList = StringUtils.getAllSubStringWithSize(paddedBinary, 6);
            for (int i = 0; i < resultList.size(); i++) {
                result = result + Base64Map.values.get(resultList.get(i));
            }
            return result;
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }

    private String reverseVersion(String SDKSemver) throws Exception {
        if (SDKSemver.split("\\.").length < 2) {
            throw new Exception("invalid semVer, must have at least two segments");
        }
        String[] versionArray = SDKSemver.split("\\.");
        for (int i = 0; i < versionArray.length; i++) {
            versionArray[i] = StringUtils.padStart(versionArray[i], 2, '0');
        }
        return StringUtils.join(StringUtils.reverseStringArray(versionArray), ".");
    }
}
