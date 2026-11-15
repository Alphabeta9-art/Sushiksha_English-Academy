package com.cloudinary;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.bumptech.glide.load.Key;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes11.dex */
public class AuthToken {
    private static final String AUTH_TOKEN_NAME = "__cld_token__";
    public static final AuthToken NULL_AUTH_TOKEN = new AuthToken().setNull();
    private static final Pattern UNSAFE_URL_CHARS_PATTERN = Pattern.compile("[ \"#%&'/:;<=>?@\\[\\\\\\]^`{|}~]");
    private List<String> acl;
    private long duration;
    private long expiration;
    private String ip;
    private boolean isNullToken;
    private String key;
    private long startTime;
    private String tokenName;

    public AuthToken() {
        this.tokenName = AUTH_TOKEN_NAME;
        this.acl = new ArrayList();
        this.isNullToken = false;
    }

    public AuthToken(String key) {
        this.tokenName = AUTH_TOKEN_NAME;
        this.acl = new ArrayList();
        this.isNullToken = false;
        this.key = key;
    }

    public AuthToken(Map options) {
        this.tokenName = AUTH_TOKEN_NAME;
        this.acl = new ArrayList();
        this.isNullToken = false;
        if (options != null) {
            this.tokenName = ObjectUtils.asString(options.get("tokenName"), this.tokenName);
            this.key = (String) options.get("key");
            this.startTime = ObjectUtils.asLong(options.get("startTime"), 0L).longValue();
            this.expiration = ObjectUtils.asLong(options.get("expiration"), 0L).longValue();
            this.ip = (String) options.get("ip");
            Object acl = options.get("acl");
            if (acl != null) {
                if (acl instanceof String) {
                    this.acl = Collections.singletonList(acl.toString());
                } else if (Collection.class.isAssignableFrom(acl.getClass())) {
                    this.acl = new ArrayList((Collection) acl);
                }
            }
            this.duration = ObjectUtils.asLong(options.get(TypedValues.TransitionType.S_DURATION), 0L).longValue();
        }
    }

    public Map<String, Object> asMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("tokenName", this.tokenName);
        result.put("key", this.key);
        result.put("startTime", Long.valueOf(this.startTime));
        result.put("expiration", Long.valueOf(this.expiration));
        result.put("ip", this.ip);
        result.put("acl", this.acl);
        result.put(TypedValues.TransitionType.S_DURATION, Long.valueOf(this.duration));
        return result;
    }

    public AuthToken tokenName(String tokenName) {
        this.tokenName = tokenName;
        return this;
    }

    public AuthToken startTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public AuthToken expiration(long expiration) {
        this.expiration = expiration;
        return this;
    }

    public AuthToken ip(String ip) {
        this.ip = ip;
        return this;
    }

    public AuthToken acl(String... acl) {
        this.acl = Arrays.asList(acl);
        return this;
    }

    public AuthToken duration(long duration) {
        this.duration = duration;
        return this;
    }

    public String generate() {
        return generate(null);
    }

    public String generate(String url) {
        if (url == null && (this.acl == null || this.acl.size() == 0)) {
            throw new IllegalArgumentException("Must provide acl or url");
        }
        long expiration = this.expiration;
        if (expiration == 0) {
            if (this.duration > 0) {
                long start = this.startTime > 0 ? this.startTime : Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis() / 1000;
                expiration = start + this.duration;
            } else {
                throw new IllegalArgumentException("Must provide either expiration or duration");
            }
        }
        ArrayList<String> tokenParts = new ArrayList<>();
        if (this.ip != null) {
            tokenParts.add("ip=" + this.ip);
        }
        if (this.startTime > 0) {
            tokenParts.add("st=" + this.startTime);
        }
        tokenParts.add("exp=" + expiration);
        if (this.acl != null && this.acl.size() > 0) {
            tokenParts.add("acl=" + escapeToLower(AuthToken$$ExternalSyntheticBackport0.m("!", this.acl)));
        }
        ArrayList<String> toSign = new ArrayList<>(tokenParts);
        if (url != null && (this.acl == null || this.acl.size() == 0)) {
            toSign.add("url=" + escapeToLower(url));
        }
        String auth = digest(StringUtils.join((List<String>) toSign, "~"));
        tokenParts.add("hmac=" + auth);
        return this.tokenName + "=" + StringUtils.join((List<String>) tokenParts, "~");
    }

    private String escapeToLower(String url) {
        String encodedUrl = StringUtils.urlEncode(url, UNSAFE_URL_CHARS_PATTERN, Charset.forName(Key.STRING_CHARSET_NAME));
        return encodedUrl;
    }

    public AuthToken copy() {
        AuthToken authToken = new AuthToken(this.key);
        authToken.tokenName = this.tokenName;
        authToken.startTime = this.startTime;
        authToken.expiration = this.expiration;
        authToken.ip = this.ip;
        authToken.acl = this.acl;
        authToken.duration = this.duration;
        return authToken;
    }

    public AuthToken merge(AuthToken other) {
        if (other.equals(NULL_AUTH_TOKEN)) {
            return other;
        }
        AuthToken merged = new AuthToken();
        merged.key = other.key != null ? other.key : this.key;
        merged.tokenName = other.tokenName != null ? other.tokenName : this.tokenName;
        merged.startTime = other.startTime != 0 ? other.startTime : this.startTime;
        merged.expiration = other.expiration != 0 ? other.expiration : this.expiration;
        merged.ip = other.ip != null ? other.ip : this.ip;
        merged.acl = other.acl != null ? other.acl : this.acl;
        merged.duration = other.duration != 0 ? other.duration : this.duration;
        return merged;
    }

    private String digest(String message) {
        byte[] binKey = StringUtils.hexStringToByteArray(this.key);
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(binKey, "HmacSHA256");
            hmac.init(secret);
            byte[] bytes = message.getBytes();
            return StringUtils.encodeHexString(hmac.doFinal(bytes)).toLowerCase();
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Cannot create authorization token.", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Cannot create authorization token.", e2);
        }
    }

    private AuthToken setNull() {
        this.isNullToken = true;
        return this;
    }

    public boolean equals(Object o) {
        if (!(o instanceof AuthToken)) {
            return false;
        }
        AuthToken other = (AuthToken) o;
        if (!this.isNullToken || !other.isNullToken) {
            if (this.key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!this.key.equals(other.key)) {
                return false;
            }
            if (!this.tokenName.equals(other.tokenName) || this.startTime != other.startTime || this.expiration != other.expiration || this.duration != other.duration) {
                return false;
            }
            if (this.ip == null) {
                if (other.ip != null) {
                    return false;
                }
            } else if (!this.ip.equals(other.ip)) {
                return false;
            }
            if (this.acl == null) {
                if (other.acl != null) {
                    return false;
                }
            } else if (!this.acl.equals(other.acl)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        if (this.isNullToken) {
            return 0;
        }
        return Arrays.asList(this.tokenName, Long.valueOf(this.startTime), Long.valueOf(this.expiration), Long.valueOf(this.duration), this.ip, this.acl).hashCode();
    }
}
