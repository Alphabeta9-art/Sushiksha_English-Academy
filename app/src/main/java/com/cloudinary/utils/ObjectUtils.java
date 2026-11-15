package com.cloudinary.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class ObjectUtils {
    public static String toISO8601(Date date) {
        DateFormat dateFormat = getDateFormat();
        return dateFormat.format(date);
    }

    public static Date fromISO8601(String date) throws ParseException {
        DateFormat dateFormat = getDateFormat();
        return (Date) dateFormat.parseObject(date);
    }

    private static DateFormat getDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public static String asString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static String asString(Object value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value.toString();
    }

    public static String serialize(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        try {
            objectOutputStream.writeObject(object);
            return new String(Base64Coder.encode(baos.toByteArray()));
        } finally {
            objectOutputStream.close();
        }
    }

    public static Object deserialize(String base64SerializedString) throws IOException, ClassNotFoundException {
        byte[] buf = Base64Coder.decode(base64SerializedString);
        return new ObjectInputStream(new ByteArrayInputStream(buf)).readObject();
    }

    public static List asArray(Object value) {
        if (value == null) {
            return Collections.EMPTY_LIST;
        }
        if (value instanceof int[]) {
            List array = new ArrayList();
            for (int i : (int[]) value) {
                array.add(new Integer(i));
            }
            return array;
        }
        if (value instanceof Object[]) {
            return Arrays.asList((Object[]) value);
        }
        if (value instanceof List) {
            return (List) value;
        }
        List array2 = new ArrayList();
        array2.add(value);
        return array2;
    }

    public static Boolean asBoolean(Object value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return asBoolean(value);
    }

    public static Boolean asBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return Boolean.valueOf("true".equals(value));
    }

    public static Float asFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Float) {
            return (Float) value;
        }
        return Float.valueOf(Float.parseFloat(value.toString()));
    }

    public static Map asMap(Object... values) {
        if (values.length % 2 != 0) {
            throw new RuntimeException("Usage - (key, value, key, value, ...)");
        }
        Map result = new HashMap(values.length / 2);
        for (int i = 0; i < values.length; i += 2) {
            result.put(values[i], values[i + 1]);
        }
        return result;
    }

    public static Map emptyMap() {
        return Collections.EMPTY_MAP;
    }

    public static String encodeMap(Object arg) {
        if (arg != null && (arg instanceof Map)) {
            Map<String, String> mapArg = (Map) arg;
            HashSet out = new HashSet();
            for (Map.Entry<String, String> entry : mapArg.entrySet()) {
                out.add(entry.getKey() + "=" + entry.getValue());
            }
            return StringUtils.join(out.toArray(), "|");
        }
        if (arg == null) {
            return null;
        }
        return arg.toString();
    }

    public static Map<String, ? extends Object> only(Map<String, ? extends Object> hash, String... keys) {
        Map<String, Object> result = new HashMap<>();
        for (String key : keys) {
            if (hash.containsKey(key)) {
                result.put(key, hash.get(key));
            }
        }
        return result;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();
        Iterator<String> itKeys = object.keys();
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    public static JSONObject toJSON(Map<String, ? extends Object> map) throws JSONException {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            json.put(field, value);
        }
        return json;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        }
        if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        }
        if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        }
        return json;
    }

    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    public static Integer asInteger(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        return Integer.valueOf(Integer.parseInt(value.toString()));
    }

    public static Long asLong(Object value, Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Long) {
            return (Long) value;
        }
        return Long.valueOf(Long.parseLong(value.toString()));
    }

    public static String toUsageApiDateFormat(Date date) {
        return new SimpleDateFormat("dd-MM-yyy").format(date);
    }

    public static String toISO8601DateOnly(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date fromISO8601DateOnly(String string) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(string);
    }
}
