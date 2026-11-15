package com.cloudinary.utils;

/* JADX INFO: loaded from: classes11.dex */
public class Base64Coder {
    private static final byte[] map2;
    private static final String systemLineSeparator = System.getProperty("line.separator");
    private static final char[] map1 = new char[64];

    static {
        int i = 0;
        char c = 'A';
        while (c <= 'Z') {
            map1[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        int i2 = i + 1;
        map1[i] = '+';
        int i3 = i2 + 1;
        map1[i2] = '/';
        map2 = new byte[128];
        for (int i4 = 0; i4 < map2.length; i4++) {
            map2[i4] = -1;
        }
        for (int i5 = 0; i5 < 64; i5++) {
            map2[map1[i5]] = (byte) i5;
        }
    }

    public static String encodeString(String s) {
        return new String(encode(s.getBytes()));
    }

    public static String encodeLines(byte[] in) {
        return encodeLines(in, 0, in.length, 76, systemLineSeparator);
    }

    public static String encodeLines(byte[] in, int iOff, int iLen, int lineLen, String lineSeparator) {
        int blockLen = (lineLen * 3) / 4;
        if (blockLen <= 0) {
            throw new IllegalArgumentException();
        }
        int lines = ((iLen + blockLen) - 1) / blockLen;
        int bufLen = (((iLen + 2) / 3) * 4) + (lineSeparator.length() * lines);
        StringBuilder buf = new StringBuilder(bufLen);
        int ip = 0;
        while (ip < iLen) {
            int l = Math.min(iLen - ip, blockLen);
            buf.append(encode(in, iOff + ip, l));
            buf.append(lineSeparator);
            ip += l;
        }
        return buf.toString();
    }

    public static char[] encode(byte[] in) {
        return encode(in, 0, in.length);
    }

    public static char[] encode(byte[] in, int iLen) {
        return encode(in, 0, iLen);
    }

    public static char[] encode(byte[] in, int iOff, int iLen) {
        int ip;
        int ip2;
        int ip3;
        int ip4;
        int oDataLen = ((iLen * 4) + 2) / 3;
        int oLen = ((iLen + 2) / 3) * 4;
        char[] out = new char[oLen];
        int ip5 = iOff;
        int iEnd = iOff + iLen;
        int op = 0;
        while (ip5 < iEnd) {
            int ip6 = ip5 + 1;
            int i0 = in[ip5] & 255;
            if (ip6 < iEnd) {
                ip = ip6 + 1;
                ip2 = in[ip6] & 255;
            } else {
                ip = ip6;
                ip2 = 0;
            }
            if (ip < iEnd) {
                ip4 = ip + 1;
                ip3 = in[ip] & 255;
            } else {
                int i = ip;
                ip3 = 0;
                ip4 = i;
            }
            int o0 = i0 >>> 2;
            int o1 = ((i0 & 3) << 4) | (ip2 >>> 4);
            int o2 = ((ip2 & 15) << 2) | (ip3 >>> 6);
            int o3 = ip3 & 63;
            int op2 = op + 1;
            out[op] = map1[o0];
            int op3 = op2 + 1;
            out[op2] = map1[o1];
            char c = '=';
            out[op3] = op3 < oDataLen ? map1[o2] : '=';
            int op4 = op3 + 1;
            if (op4 < oDataLen) {
                c = map1[o3];
            }
            out[op4] = c;
            op = op4 + 1;
            ip5 = ip4;
        }
        return out;
    }

    public static String decodeString(String s) {
        return new String(decode(s));
    }

    public static byte[] decodeLines(String s) {
        char[] buf = new char[s.length()];
        int p = 0;
        for (int ip = 0; ip < s.length(); ip++) {
            char c = s.charAt(ip);
            if (c != ' ' && c != '\r' && c != '\n' && c != '\t') {
                buf[p] = c;
                p++;
            }
        }
        return decode(buf, 0, p);
    }

    public static byte[] decode(String s) {
        return decode(s.toCharArray());
    }

    public static byte[] decode(char[] in) {
        return decode(in, 0, in.length);
    }

    public static byte[] decode(char[] in, int iOff, int iLen) {
        int ip;
        char c;
        char c2;
        int ip2;
        if (iLen % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        int iLen2 = iLen;
        while (iLen2 > 0 && in[(iOff + iLen2) - 1] == '=') {
            iLen2--;
        }
        int oLen = (iLen2 * 3) / 4;
        byte[] out = new byte[oLen];
        int ip3 = iOff;
        int iEnd = iOff + iLen2;
        int op = 0;
        while (ip3 < iEnd) {
            int ip4 = ip3 + 1;
            char c3 = in[ip3];
            int ip5 = ip4 + 1;
            char c4 = in[ip4];
            if (ip5 < iEnd) {
                ip = ip5 + 1;
                c = in[ip5];
            } else {
                ip = ip5;
                c = 'A';
            }
            if (ip < iEnd) {
                ip2 = ip + 1;
                c2 = in[ip];
            } else {
                int i = ip;
                c2 = 'A';
                ip2 = i;
            }
            if (c3 > 127 || c4 > 127 || c > 127 || c2 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int b0 = map2[c3];
            int b1 = map2[c4];
            int b2 = map2[c];
            int b3 = map2[c2];
            if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int o0 = (b0 << 2) | (b1 >>> 4);
            int o1 = ((b1 & 15) << 4) | (b2 >>> 2);
            int iLen3 = iLen2;
            int iLen4 = ((b2 & 3) << 6) | b3;
            byte[] out2 = out;
            int op2 = op + 1;
            out2[op] = (byte) o0;
            if (op2 < oLen) {
                out2[op2] = (byte) o1;
                op2++;
            }
            if (op2 >= oLen) {
                op = op2;
            } else {
                out2[op2] = (byte) iLen4;
                op = op2 + 1;
            }
            iLen2 = iLen3;
            ip3 = ip2;
            out = out2;
        }
        return out;
    }

    private Base64Coder() {
    }

    public static String encodeURLSafeString(String s) {
        return encodeURLSafeString(s.getBytes());
    }

    public static String encodeURLSafeString(byte[] digest) {
        char[] encode = encode(digest);
        for (int i = 0; i < encode.length; i++) {
            if (encode[i] == '+') {
                encode[i] = '-';
            } else if (encode[i] == '/') {
                encode[i] = '_';
            }
        }
        return new String(encode);
    }
}
