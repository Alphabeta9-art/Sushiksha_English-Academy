package com.cloudinary.utils;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class Base64Map {
    public static Map<String, String> values = new HashMap();

    static {
        values.put("000000", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        values.put("000001", "B");
        values.put("000010", "C");
        values.put("000011", "D");
        values.put("000100", ExifInterface.LONGITUDE_EAST);
        values.put("000101", "F");
        values.put("000110", "G");
        values.put("000111", "H");
        values.put("001000", "I");
        values.put("001001", "J");
        values.put("001010", "K");
        values.put("001011", "L");
        values.put("001100", "M");
        values.put("001101", "N");
        values.put("001110", "O");
        values.put("001111", "P");
        values.put("010000", "Q");
        values.put("010001", "R");
        values.put("010010", ExifInterface.LATITUDE_SOUTH);
        values.put("010011", ExifInterface.GPS_DIRECTION_TRUE);
        values.put("010100", "U");
        values.put("010101", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        values.put("010110", ExifInterface.LONGITUDE_WEST);
        values.put("010111", "X");
        values.put("011000", "Y");
        values.put("011001", "Z");
        values.put("011010", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY);
        values.put("011011", "b");
        values.put("011100", "c");
        values.put("011101", "d");
        values.put("011110", "e");
        values.put("011111", "f");
        values.put("100000", "g");
        values.put("100001", CmcdHeadersFactory.STREAMING_FORMAT_HLS);
        values.put("100010", "i");
        values.put("100011", "j");
        values.put("100100", "k");
        values.put("100101", CmcdHeadersFactory.STREAM_TYPE_LIVE);
        values.put("100110", "m");
        values.put("100111", "n");
        values.put("101000", "o");
        values.put("101001", "p");
        values.put("101010", "q");
        values.put("101011", "r");
        values.put("101100", CmcdHeadersFactory.STREAMING_FORMAT_SS);
        values.put("101101", "t");
        values.put("101110", "u");
        values.put("101111", "v");
        values.put("110000", "w");
        values.put("110001", "x");
        values.put("110010", "y");
        values.put("110011", "z");
        values.put("110100", "0");
        values.put("110101", "1");
        values.put("110110", ExifInterface.GPS_MEASUREMENT_2D);
        values.put("110111", ExifInterface.GPS_MEASUREMENT_3D);
        values.put("111000", "4");
        values.put("111001", "5");
        values.put("111010", "6");
        values.put("111011", "7");
        values.put("111100", "8");
        values.put("111101", "9");
        values.put("111110", "+");
        values.put("111111", DomExceptionUtils.SEPARATOR);
    }
}
