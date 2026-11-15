package com.cloudinary.utils;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes11.dex */
public class StringUtils {
    public static final String EMPTY = "";
    protected static final char[] hexArray = "0123456789abcdef".toCharArray();

    public static String join(List<String> list, String separator) {
        if (list == null) {
            return null;
        }
        return join(list.toArray(), separator, 0, list.size());
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Collection<String> collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.toArray(new String[collection.size()]), separator, 0, collection.size());
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String encodeHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Length of string to parse must be even.");
        }
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String escapeHtml(String input) {
        return HtmlEscape.escapeTextArea(input);
    }

    public static boolean isNotBlank(Object input) {
        if (input == null) {
            return false;
        }
        return !isBlank(input.toString());
    }

    public static boolean isNotBlank(String input) {
        return !isBlank(input);
    }

    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }

    public static boolean isBlank(String input) {
        int strLen;
        if (input == null || (strLen = input.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String read(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int length = in.read(buffer);
            if (length != -1) {
                baos.write(buffer, 0, length);
            } else {
                return new String(baos.toByteArray());
            }
        }
    }

    public static boolean isRemoteUrl(String file) {
        return file.matches("ftp:.*|https?:.*|s3:.*|gs:.*|data:([\\w-]+/[\\w-]+(\\+[\\w-]+)?)?(;[\\w-]+=[\\w-]+)*;base64,([a-zA-Z0-9/+\n=]+)");
    }

    public static String urlEncode(String url, Pattern unsafe, Charset charset) {
        StringBuffer sb = new StringBuffer(url.length());
        Matcher matcher = unsafe.matcher(url);
        while (matcher.find()) {
            String str = matcher.group(0);
            byte[] bytes = str.getBytes(charset);
            StringBuilder escaped = new StringBuilder(str.length() * 3);
            for (byte aByte : bytes) {
                escaped.append('%');
                char ch = Character.forDigit((aByte >> 4) & 15, 16);
                escaped.append(ch);
                char ch2 = Character.forDigit(aByte & Ascii.SI, 16);
                escaped.append(ch2);
            }
            matcher.appendReplacement(sb, Matcher.quoteReplacement(escaped.toString().toLowerCase()));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String mergeToSingleUnderscore(String s) {
        StringBuffer buffer = new StringBuffer();
        boolean inMerge = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ' || c == '_') {
                if (!inMerge) {
                    buffer.append('_');
                }
                inMerge = true;
            } else {
                inMerge = false;
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    public static boolean isVariable(String s) {
        if (s == null || s.length() < 2 || !s.startsWith("$") || !Character.isLetter(s.charAt(1))) {
            return false;
        }
        for (int i = 2; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static String replaceIfFirstChar(String s, char c, String replacement) {
        return s.charAt(0) == c ? replacement + s.substring(1) : s;
    }

    public static boolean isHttpUrl(String s) {
        String lowerCaseSource = s.toLowerCase();
        return lowerCaseSource.startsWith("https:/") || lowerCaseSource.startsWith("http:/");
    }

    public static String removeStartingChars(String s, char c) {
        int lastToRemove = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                lastToRemove = i;
            } else if (s.charAt(i) != c) {
                break;
            }
        }
        return lastToRemove < 0 ? s : s.substring(lastToRemove + 1);
    }

    public static boolean startWithVersionString(String publicId) {
        if (publicId.startsWith(DomExceptionUtils.SEPARATOR)) {
            publicId = publicId.substring(1);
        }
        return publicId.length() > 1 && publicId.startsWith("v") && Character.isDigit(publicId.charAt(1));
    }

    public static String mergeSlashesInUrl(String url) {
        StringBuilder builder = new StringBuilder();
        boolean prevIsColon = false;
        boolean inMerge = false;
        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            if (c == ':') {
                prevIsColon = true;
                builder.append(c);
            } else {
                if (c == '/') {
                    if (prevIsColon) {
                        builder.append(c);
                        inMerge = false;
                    } else {
                        if (!inMerge) {
                            builder.append(c);
                        }
                        inMerge = true;
                    }
                } else {
                    inMerge = false;
                    builder.append(c);
                }
                prevIsColon = false;
            }
        }
        return builder.toString();
    }

    public static String emptyIfNull(String str) {
        return isEmpty(str) ? "" : str;
    }

    static String[] reverseStringArray(String[] strings) {
        Collections.reverse(Arrays.asList(strings));
        return strings;
    }

    public static String padStart(String inputString, int length, char paddingCharacter) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(paddingCharacter);
        }
        sb.append(inputString);
        return sb.toString();
    }

    public static List<String> getAllSubStringWithSize(String text, int n) {
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile(".{1," + n + "}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String match = text.substring(matcher.start(), matcher.end());
            results.add(match);
        }
        return results;
    }
}
